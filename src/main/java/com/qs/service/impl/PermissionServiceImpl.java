package com.qs.service.impl;

import com.qs.entity.Permission;
import com.qs.entity.RolePermission;
import com.qs.service.PermissionService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private SessionFactory sessionFactory;


    public List<Permission> getPermissionList(String roleId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RolePermission.class);
        List list = criteria.createAlias("role", "r").add(
                Restrictions.eq("r.roleId", roleId)
        ).list();
        return null;
    }
}
