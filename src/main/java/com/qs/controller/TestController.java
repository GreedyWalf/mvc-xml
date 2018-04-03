package com.qs.controller;

import com.qs.entity.User;
import com.qs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RestController
public class TestController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "test")
    public String test(){
        userService.save(new User("qinyupeng","000000","695830237@qq.com","15856999769"));
        return "Hello Mybatis";
    }
}
