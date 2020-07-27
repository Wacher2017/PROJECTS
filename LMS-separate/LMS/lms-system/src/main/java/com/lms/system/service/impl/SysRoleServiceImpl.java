package com.lms.system.service.impl;

import com.lms.system.domain.SysRole;
import com.lms.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色 业务层处理
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return null;
    }

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        return null;
    }

    @Override
    public List<SysRole> selectRoleAll() {
        return null;
    }

    @Override
    public List<Integer> selectRoleListByUserId(Long userId) {
        return null;
    }

    @Override
    public SysRole selectRoleById(Long roleId) {
        return null;
    }

    @Override
    public String checkRoleNameUnique(SysRole role) {
        return null;
    }

    @Override
    public String checkRoleKeyUnique(SysRole role) {
        return null;
    }

    @Override
    public void checkRoleAllowed(SysRole role) {

    }

    @Override
    public int countUserRoleByRoleId(Long roleId) {
        return 0;
    }

    @Override
    public int insertRole(SysRole role) {
        return 0;
    }

    @Override
    public int updateRole(SysRole role) {
        return 0;
    }

    @Override
    public int updateRoleStatus(SysRole role) {
        return 0;
    }

    @Override
    public int authDataScope(SysRole role) {
        return 0;
    }

    @Override
    public int deleteRoleById(Long roleId) {
        return 0;
    }

    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        return 0;
    }

}
