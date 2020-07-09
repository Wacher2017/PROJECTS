package com.lms.system.service.impl;

import com.lms.system.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 菜单 业务层处理
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        Set<String> permsSet = new HashSet<>();
        /*List<String> perms = menuMapper.selectPermsByUserId(userId);
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }*/
        return permsSet;
    }
}
