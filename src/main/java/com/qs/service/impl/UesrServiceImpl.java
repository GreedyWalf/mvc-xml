package com.qs.service.impl;

import com.qs.dao.UserDao;
import com.qs.entity.User;
import com.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UesrServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
