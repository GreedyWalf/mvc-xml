package com.qs.service.impl;

import com.qs.entity.User;
import com.qs.service.UserService;
import com.qs.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        System.out.println("--->>userMapper=" + userMapper);
        return userMapper.getAllUsers();
    }
}
