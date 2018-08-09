package com.qs.service;

import com.qs.entity.UserInfo;

public interface UserInfoService {

    UserInfo getUserInfoByUserName(String userName);

    void save(UserInfo userInfo);

}
