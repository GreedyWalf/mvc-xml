package com.qs.service;

import com.qs.entity.Role;
import com.qs.entity.UserRole;

import java.util.List;

public interface RoleService {

    List<Role> getRoleListByUserId(String userId);
}
