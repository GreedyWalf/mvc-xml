package com.qs.service;

import com.qs.entity.user.User;
import com.qs.service.base.BaseService;

public interface UserService extends BaseService<User> {

    User findByUserName(String userName);

    /**
     * 插入保存实体数据
     * <p>必须设置实体的主键，返回的主键为实体设置的主键</p>
     *
     * @param user 需要保存的实体
     * @return 主键
     */
    String insertUser(User user);
}
