package com.qs.controller;

import com.qs.entity.User;
import com.qs.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
