package com.lms.system.service.impl;

import com.lms.common.enums.UserStatus;
import com.lms.system.domain.SysUser;
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
    public SysUser selectUserByUserName(String userName) {
        SysUser user = new SysUser();
        user.setUserName("admin");
        user.setSalt("a5e2ee");
        user.setPassword("78a684193400c415ce6ad24b27a0e374");
        user.setDelFlag(UserStatus.OK.getCode());
        user.setStatus(UserStatus.OK.getCode());
        return user;
    }

    @Override
    public SysUser selectUserById(Long userId) {
        return null;
    }

    @Override
    public String selectUserRoleGroup(String userName) {
        return null;
    }

    @Override
    public String selectUserPostGroup(String userName) {
        return null;
    }

    @Override
    public String checkUserNameUnique(String userName) {
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
    public int insertUser(SysUser user) {
        return 0;
    }

    @Override
    public int updateUser(SysUser user) {
        return 0;
    }

    @Override
    public int updateUserStatus(SysUser user) {
        return 0;
    }

    @Override
    public int updateUserProfile(SysUser user) {
        return 0;
    }

    @Override
    public boolean updateUserAvatar(String userName, String avatar) {
        return false;
    }

    @Override
    public int resetPwd(SysUser user) {
        return 0;
    }

    @Override
    public int resetUserPwd(String userName, String password) {
        return 0;
    }

    @Override
    public int deleteUserById(Long userId) {
        return 0;
    }

    @Override
    public int deleteUserByIds(Long[] userIds) {
        return 0;
    }

    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName) {
        return null;
    }
}
