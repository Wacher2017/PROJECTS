package com.lms.system.service.impl;

import com.lms.common.enums.UserStatus;
import com.lms.system.domain.SysUser;
import com.lms.system.domain.SysUserRole;
import com.lms.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 业务层处理
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return null;
    }

    @Override
    public List<SysUser> selectAllocatedList(SysUser user) {
        return null;
    }

    @Override
    public List<SysUser> selectUnallocatedList(SysUser user) {
        return null;
    }

    @Override
    public SysUser selectUserByLoginName(String userName) {
        SysUser user = new SysUser();
        user.setLoginName("admin");
        user.setUserName("管理员");
        user.setSalt("a5e2ee");
        user.setPassword("78a684193400c415ce6ad24b27a0e374");
        user.setDelFlag(UserStatus.OK.getCode());
        user.setStatus(UserStatus.OK.getCode());
        return user;
    }

    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public SysUser selectUserByEmail(String email) {
        return null;
    }

    @Override
    public SysUser selectUserById(Long userId) {
        return null;
    }

    @Override
    public List<SysUserRole> selectUserRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public int deleteUserById(Long userId) {
        return 0;
    }

    @Override
    public int deleteUserByIds(String ids) throws Exception {
        return 0;
    }

    @Override
    public int insertUser(SysUser user) {
        return 0;
    }

    @Override
    public boolean registerUser(SysUser user) {
        return false;
    }

    @Override
    public int updateUser(SysUser user) {
        return 0;
    }

    @Override
    public int updateUserInfo(SysUser user) {
        return 0;
    }

    @Override
    public void insertUserAuth(Long userId, Long[] roleIds) {

    }

    @Override
    public int resetUserPwd(SysUser user) {
        return 0;
    }

    @Override
    public String checkLoginNameUnique(String loginName) {
        return null;
    }

    @Override
    public String checkPhoneUnique(SysUser user) {
        return null;
    }

    @Override
    public String checkEmailUnique(SysUser user) {
        return null;
    }

    @Override
    public void checkUserAllowed(SysUser user) {

    }

    @Override
    public String selectUserRoleGroup(Long userId) {
        return null;
    }

    @Override
    public String selectUserPostGroup(Long userId) {
        return null;
    }

    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName) {
        return null;
    }

    @Override
    public int changeStatus(SysUser user) {
        return 0;
    }

}
