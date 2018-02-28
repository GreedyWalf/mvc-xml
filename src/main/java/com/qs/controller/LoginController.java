package com.qs.controller;

import com.qs.base.context.ExecutionContext;
import com.qs.entity.user.User;
import com.qs.service.UserService;
import com.qs.util.JsonResult;
import com.qs.util.JsonStatus;
import com.qs.util.RequestContextFactory;
import com.qs.util.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate sessionRedisTemplate;


    @RequestMapping(value = "/login")
    public String login() {
        return "login/login";
    }


    @RequestMapping(value = "/ajaxLogin")
    @ResponseBody
    public JsonResult ajaxLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        String userName = user.getUserName();
        User userOld = userService.findByUserName(user.getUserName());
        if (userOld == null) {
            jsonResult.setStatus(JsonStatus.ERROR);
            jsonResult.setMessage("登录信息不存在");
            return jsonResult;
        }

        if (!userOld.getPassword().equals(user.getPassword())) {
            jsonResult.setStatus(JsonStatus.ERROR);
            jsonResult.setMessage("密码不正确");
            return jsonResult;
        }

        //用户id
        String userId = userOld.getId();
        //后台生成一个sessionId，存储到cookie和redis中
        String sessionId = UUIDGenerator.uuid();
        String sessionKey = "LOGIN_" + userId;

        RequestContextFactory.addCookie(response, "session_id", sessionId, -1);
        sessionRedisTemplate.opsForValue().set(sessionKey, sessionId);
        //这里失效时间暂时设置为60s，调试使用
        sessionRedisTemplate.expire(sessionKey, 60, TimeUnit.SECONDS);

        Map<String, String> contextMap = new HashMap<String, String>();
        contextMap.put(ExecutionContext.USER_ID, userId);
        contextMap.put(ExecutionContext.USER_NAME, userName);
        sessionRedisTemplate.opsForValue().set(sessionId, contextMap);

        jsonResult.setMessage("恭喜你，登录成功啦！！");
        return jsonResult;
    }

    @RequestMapping(value = "/index")
    public String showIndex() {
        return "/index/index";
    }

    private void addCookie(String name, String value, int maxAge, HttpServletResponse response) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(value)) {
            return;
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @RequestMapping("/register")
    private String register() {
        return "/login/register";
    }

    @RequestMapping("/ajaxRegister")
    @ResponseBody
    private JsonResult ajaxRegister(User user) {
        JsonResult jsonResult = new JsonResult();
        if (StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getUserName())) {
            jsonResult.setMessage("用户名或密码为空！");
            jsonResult.setStatus(JsonStatus.ERROR);
            return jsonResult;
        }

        User userOld = userService.findByUserName(user.getUserName());
        if (userOld != null) {
            jsonResult.setMessage("当前用户名已经注册，换一个吧");
        }

        String userId = UUIDGenerator.uuid();
        user.setId(userId);
        user.setCreateTime(new Date());
        user.setCreateBy(userId);
        userService.insertUser(user);
        return jsonResult;
    }

}
