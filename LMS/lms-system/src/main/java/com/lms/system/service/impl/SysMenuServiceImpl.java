package com.lms.system.service.impl;

import com.lms.common.core.domain.Ztree;
import com.lms.system.domain.SysMenu;
import com.lms.system.domain.SysRole;
import com.lms.system.domain.SysUser;
import com.lms.system.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单 业务层处理
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Override
    public List<SysMenu> selectMenusByUser(SysUser user) {
        return null;
    }

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
        return null;
    }

    @Override
    public List<SysMenu> selectMenuAll(Long userId) {
        return null;
    }

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Ztree> roleMenuTreeData(SysRole role, Long userId) {
        return null;
    }

    @Override
    public List<Ztree> menuTreeData(Long userId) {
        return null;
    }

    @Override
    public Map<String, String> selectPermsAll(Long userId) {
        return null;
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    @Override
    public SysMenu selectMenuById(Long menuId) {
        return null;
    }

    @Override
    public int selectCountMenuByParentId(Long parentId) {
        return 0;
    }

    @Override
    public int selectCountRoleMenuByMenuId(Long menuId) {
        return 0;
    }

    @Override
    public int insertMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        return null;
    }

}
