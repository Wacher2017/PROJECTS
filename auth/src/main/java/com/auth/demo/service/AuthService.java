package com.auth.demo.service;

import com.auth.demo.domain.ResponseUserToken;
import com.auth.demo.domain.UserDetail;

/**
 * @author Chunming_Wang
 */
public interface AuthService {

    /**
     * 注册用户
     */
    UserDetail register(UserDetail userDetail);

    /**
     * 登陆
     */
    ResponseUserToken login(String userName, String password);

    /**
     * 登出
     */
    void logout(String token);

    /**
     * 刷新Token
     */
    ResponseUserToken refresh(String oldToken);

    /**
     * 根据Token获取用户信息
     */
    UserDetail getUserByToken(String token);

    /**
     * 根据用户名获取Token
     */
    String getTokenByUserName(String userName);

}
