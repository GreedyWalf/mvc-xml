package com.qs.service;

import com.qs.entity.User;

public interface UserService {

    User findByUsername(String username);

    int saveUser(User user);

    int updateUser(User user);
}
