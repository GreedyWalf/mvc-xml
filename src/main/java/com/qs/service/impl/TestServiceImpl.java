package com.qs.service.impl;


import com.qs.service.TestService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("testService")
public class TestServiceImpl implements TestService {
    @Resource
    private SessionFactory sessionFactory;

    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    public String test(){
        Session currentSession = sessionFactory.getCurrentSession();
        return "test";
    }
}
