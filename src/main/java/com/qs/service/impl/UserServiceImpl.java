package com.qs.service.impl;

import com.qs.entity.user.User;
import com.qs.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Resource
    private SessionFactory sessionFactory;


    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public User findByUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new RuntimeException("param userName is null or empty!");
        }

        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("userName", userName))
                .setMaxResults(1);
        return (User) criteria.uniqueResult();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
    public String insertUser(User user) {
        if(user == null || StringUtils.isBlank(user.getId())){
            throw new RuntimeException("user or userId is null or empty!");
        }

        String userId = user.getId();
        //调用session的save方法后，会自动生成主键
        String _id = save(user);
        //将自动生成的主键更新为自己设置的主键
        update("id", userId, user);
        return userId;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<User> getUsers() {
        Criteria criteria = createCriteria();
        return criteria.list();
    }
}
