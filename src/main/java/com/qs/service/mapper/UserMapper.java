package com.qs.service.mapper;


import com.qs.entity.User;

public interface UserMapper {

    int saveUser(User user);

    int updateUser(User user);

    User findUserByUsername(String username);

}
