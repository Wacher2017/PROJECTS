package com.lms.system.service.impl;

import com.lms.system.domain.SysMenu;
import com.lms.system.pojo.RouterVo;
import com.lms.system.pojo.TreeSelect;
import com.lms.system.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 菜单 业务层处理
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Override
    public List<SysMenu> selectMenuList(Long userId) {
        return null;
    }

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
        return null;
    }

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Integer> selectMenuListByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        return null;
    }

    @Override
    public List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        return null;
    }

    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus) {
        return null;
    }

    @Override
    public SysMenu selectMenuById(Long menuId) {
        return null;
    }

    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return false;
    }

    @Override
    public boolean checkMenuExistRole(Long menuId) {
        return false;
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
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        return null;
    }

}
