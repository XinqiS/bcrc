package com.sooka.module.web.system.service;

import com.sooka.mybatis.model.TSysPermission;
import com.sooka.mybatis.model.TSysRole;
import com.sooka.mybatis.model.TSysRolePermission;

import java.util.List;

/**
 * Created by binary on 2017/4/14.
 */
public interface RoleService {

    TSysRole findByid(Integer id);

    List<TSysRole> findByUserId(Integer userId);

    String delete(Integer id);

    String update(TSysRole role,Integer[] permissionId);

    String save(TSysRole role,Integer[] permissionId);

    List<TSysRole> findAll();

    List<TSysPermission> findPermissonByPid(Integer pid);

    Integer findPermissionCountByRoleId(Integer roleId,Integer permissionId);

    Integer  SaveRolePermission(TSysRolePermission rolePermission);

    TSysPermission findPermissonByid(Integer pid);

    String save(TSysPermission permission);

    String update(TSysPermission permission);

    String delectPermissionById(Integer id);

}
