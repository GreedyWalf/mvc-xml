package com.qs.dao;

import com.qs.entity.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    List<User> getAllUsers();
}
