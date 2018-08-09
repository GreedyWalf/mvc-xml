package com.qs.service.impl;

import com.qs.entity.Role;
import com.qs.entity.UserInfo;
import com.qs.service.RoleService;
import com.qs.service.UserInfoService;
import org.apache.shiro.util.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private RoleService roleService;


    @Transactional
    public UserInfo getUserInfoByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from UserInfo where userName=:userName");
        query.setString("userName", userName);
        List list = query.list();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        UserInfo userInfo = (UserInfo) list.get(0);
        String userId = userInfo.getUserId();
        List<Role> roleList = roleService.getRoleListByUserId(userId);
        userInfo.setRoleList(roleList);
        return userInfo;
    }

    public void save(UserInfo userInfo) {
        Session session = sessionFactory.getCurrentSession();
        session.save(userInfo);
    }
}
