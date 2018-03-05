package com.qs.controller;

import com.qs.entity.user.User;
import com.qs.service.UserService;
import com.qs.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/listUser")
    public JsonResult listUser(){
        JsonResult jsonResult = new JsonResult();
        List<User> users = userService.getUsers();
        jsonResult.setData(users);
        return jsonResult;
    }

}
