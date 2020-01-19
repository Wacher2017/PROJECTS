package com.auth.demo.mapper;

import com.auth.demo.domain.Role;
import com.auth.demo.domain.UserDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Chunming_Wang
 */
@Repository
public interface AuthMapper {

    /**
     * 根据用户名查找用户
     * @param name 用户名
     * @return UserDetail
     */
    UserDetail findByUsername(@Param("name") String name);

    /**
     * 创建新用户
     * @param userDetail 用户信息
     */
    void insert(UserDetail userDetail);

    /**
     * 创建用户角色
     * @param userId 用户id
     * @param roleId 角色id
     * @return int
     */
    int insertRole(@Param("userId") long userId, @Param("roleId") long roleId);

    /**
     * 根据角色id查找角色
     * @param roleId 角色id
     * @return Role
     */
    Role findRoleById(@Param("roleId") long roleId);

    /**
     * 根据用户id查找该用户角色
     * @param userId 用户id
     * @return Role
     */
    Role findRoleByUserId(@Param("userId") long userId);

}
