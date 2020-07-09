package com.lms.system.service.impl;

import com.lms.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色 业务层处理
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Override
    public Set<String> selectRoleKeys(Long userId) {
        Set<String> permsSet = new HashSet<>();
        /*List<SysRole> perms = roleMapper.selectRolesByUserId(userId);
        for (SysRole perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }*/
        return permsSet;
    }

}
