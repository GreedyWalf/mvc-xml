package com.qs.service;

import com.qs.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getPermissionList(String roleId);

}
