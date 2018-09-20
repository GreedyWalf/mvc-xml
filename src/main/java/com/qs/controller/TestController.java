package com.qs.controller;

import com.qs.entity.User;
import com.qs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class TestController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }
}
