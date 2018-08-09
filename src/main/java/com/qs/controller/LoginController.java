package com.qs.controller;

import com.qs.entity.UserInfo;
import com.qs.service.PermissionService;
import com.qs.service.UserInfoService;
import com.qs.util.JsonResult;
import com.qs.util.JsonStatus;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private PermissionService permissionService;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/content")
    public String content(){
        return "content";
    }

    /**
     * 跳转到登录页
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }


    /**
     * 处理登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 返回状态
     */
    @RequestMapping(value = "/ajaxLogin")
    @ResponseBody
    public JsonResult ajaxLogin(String userName, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName.trim(),
                DigestUtils.md5Hex(password).toCharArray());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return new JsonResult(JsonStatus.ERROR, "用户名不存在");
        } catch (IncorrectCredentialsException e) {
            return new JsonResult(JsonStatus.ERROR, "密码不存在");
        }

        return new JsonResult(JsonStatus.SUCCESS, "登录成功！");
    }

    /**
     * 登出
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    @ResponseBody
    public JsonResult logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new JsonResult(JsonStatus.SUCCESS, "成功登出！");
    }


    @RequestMapping(value = "/saveUser")
    @ResponseBody
    public JsonResult saveUser(UserInfo userInfo) {
        userInfoService.save(userInfo);
        return new JsonResult(JsonStatus.SUCCESS, "新增人员成功");
    }
}
