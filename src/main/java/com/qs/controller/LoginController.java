package com.qs.controller;

import com.qs.base.common.SessionAccessor;
import com.qs.entity.user.User;
import com.qs.service.UserService;
import com.qs.util.JsonResult;
import com.qs.util.JsonStatus;
import com.qs.util.UUIDGenerator;
import com.qs.util.WebConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String, Map<String, String>> sessionRedisTemplate;
    @Resource
    private SessionAccessor sessionAccessor;

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

        //将登录信息保存在redis中
        Map<String, String> contextMap = doLogin(userOld);
        //将登录用户的sessionId存储在cookie中
        sessionAccessor.store(request, response, contextMap);
        jsonResult.setMessage("恭喜你，登录成功啦！！");
        return jsonResult;
    }

    private Map<String, String> doLogin(User user) {
        Map<String, String> contextMap = new HashMap<String, String>();
        contextMap.put(WebConstant.USER_ID, user.getId());
        contextMap.put(WebConstant.USER_NAME, user.getUserName());
        //将当前登录的用户id设置为sessionId
        String sessionId = user.getId();
        contextMap.put(WebConstant.SESSION_ID, sessionId);

        //将登录的用户信息存储在redis中
        String sessionKey = "LOGIN_" + sessionId;
        sessionRedisTemplate.opsForValue().set(sessionKey, contextMap);
        sessionRedisTemplate.expire(sessionKey, Integer.parseInt(sessionAccessor.getSessionMaxTime()), TimeUnit.SECONDS);
        return contextMap;
    }

    @RequestMapping(value = "/index")
    public String showIndex() {
        return "/index/index";
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

    /**
     * 测试下载
     */
    @RequestMapping(value = "/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = "download.jpg";
        fileName = URLEncoder.encode(fileName, "utf-8");
        response.setContentType("image/jpeg");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        InputStream resourceAsStream = request.getServletContext().getResourceAsStream("/static/298548.jpg");
        OutputStream outputStream = response.getOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while (-1 != (len = resourceAsStream.read(buff))) {
            outputStream.write(buff, 0, len);
        }

        resourceAsStream.close();
        outputStream.close();
    }
}