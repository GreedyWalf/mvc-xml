package com.qs.controller;

import com.qs.entity.User;
import com.qs.utils.JsonResult;
import com.qs.utils.JsonStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @RequestMapping(value = "/ajaxLogin")
    @ResponseBody
    public JsonResult ajaxLogin(User user) {
        JsonResult jsonResult = new JsonResult();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            //执行subject.login()方法时，会执行自定义的realm中do
            subject.login(token);
        } catch (UnknownAccountException e) {
            jsonResult.setMsg("用户名不存在");
            return jsonResult;
        } catch (LockedAccountException e) {
            jsonResult.setMsg("用户被锁定");
            return jsonResult;
        } catch (IncorrectCredentialsException e) {
            jsonResult.setMsg("密码不存在");
            return jsonResult;
        } catch (ExcessiveAttemptsException e) {
            jsonResult.setMsg("尝试输入错误次数过多");
            return jsonResult;
        }

        jsonResult.setStatus(JsonStatus.SUCCESS);
        jsonResult.setMsg("登录成功!");
        return jsonResult;
    }


    @RequestMapping(value = "/getMap")
    @ResponseBody
    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("test", "this is test");
        return map;
    }


    @RequestMapping(value = "/logout")
    @ResponseBody
    public JsonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new JsonResult(JsonStatus.SUCCESS, "退出成功！");
    }

}
