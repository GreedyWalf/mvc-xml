package com.qs.service.impl;

import com.qs.entity.Role;
import com.qs.entity.UserRole;
import com.qs.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private SessionFactory sessionFactory;

    public List<Role> getRoleListByUserId(String userId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(UserRole.class);
        List<UserRole> userRoleList = criteria.add(
                Restrictions.eq("userId", userId)
        ).list();

        if(CollectionUtils.isEmpty(userRoleList)){
            return new ArrayList<Role>();
        }

        List<Role> roleList = new ArrayList<Role>();
        for(UserRole userRole : userRoleList){
            roleList.add(userRole.getRole());
        }

        return roleList;
    }
}
