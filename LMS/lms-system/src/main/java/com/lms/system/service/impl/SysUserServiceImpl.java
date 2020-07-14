package com.lms.system.service.impl;

import com.lms.common.core.context.StaticDataContext;
import com.lms.common.enums.UserStatus;
import com.lms.system.domain.SysUser;
import com.lms.system.domain.SysUserRole;
import com.lms.system.service.ISysUserService;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        Map<String, Object> map = StaticDataContext.getStaticDataContext();
        if (MapUtils.isNotEmpty(StaticDataContext.getStaticDataContext())
                && map.containsKey("user") && map.get("user") != null) {
            //noinspection unchecked
            List<SysUser> userList = (List<SysUser>) map.get("user");
            return userList.stream()
                    .filter(sysUser -> phoneNumber.equals(sysUser.getMobile()))
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public SysUser selectUserByEmail(String email) {
        Map<String, Object> map = StaticDataContext.getStaticDataContext();
        if (MapUtils.isNotEmpty(StaticDataContext.getStaticDataContext())
                && map.containsKey("user") && map.get("user") != null) {
            //noinspection unchecked
            List<SysUser> userList = (List<SysUser>) map.get("user");
            return userList.stream()
                    .filter(sysUser -> email.equals(sysUser.getEmail()))
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public SysUser selectUserById(Long userId) {
        Map<String, Object> map = StaticDataContext.getStaticDataContext();
        if (MapUtils.isNotEmpty(StaticDataContext.getStaticDataContext())
                && map.containsKey("user") && map.get("user") != null) {
            //noinspection unchecked
            List<SysUser> userList = (List<SysUser>) map.get("user");
            return userList.stream()
                    .filter(sysUser -> userId.equals(sysUser.getUserId()))
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public List<SysUserRole> selectUserRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public int deleteUserById(Long userId) {
        SysUser user = selectUserById(userId);
        if(user == null) return 0;
        user.setStatus("1");
        user.setDelFlag("2");
        return updateUserInfo(user);
    }

    @Override
    public int deleteUserByIds(String ids) throws Exception {
       return 0;
    }

    @Override
    public int insertUser(SysUser user) {
        try {
            List<SysUser> userList = new ArrayList<>();
            Map<String, Object> map = StaticDataContext.getStaticDataContext();
            if (MapUtils.isNotEmpty(StaticDataContext.getStaticDataContext())
                    && map.containsKey("user") && map.get("user") != null) {
                //noinspection unchecked
                userList = (List<SysUser>) map.get("user");
            }
            user.setUserId(userList.size() + 1L);
            userList.add(user);
            map.put("user", userList);
            StaticDataContext.setStaticDataContext(map);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean registerUser(SysUser user) {
        return insertUser(user) > 0;
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
