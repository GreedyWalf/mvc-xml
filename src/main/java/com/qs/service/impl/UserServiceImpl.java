package com.qs.service.impl;

import com.qs.entity.User;
import com.qs.service.UserService;
import com.qs.service.mapper.UserMapper;
import com.qs.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordHelper passwordHelper;

    public User findByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public int saveUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        return userMapper.saveUser(user);
    }

    public int updateUser(User user) {
        passwordHelper.encryptPassword(user);
        return userMapper.updateUser(user);
    }
}
