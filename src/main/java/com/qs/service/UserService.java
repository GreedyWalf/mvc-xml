package com.qs.service;

import com.qs.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> getAllUsers();
}
