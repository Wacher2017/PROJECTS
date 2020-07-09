package com.lms.framework.shiro.service;

import com.lms.system.domain.SysUser;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 */
@Component
public class SysLoginService {

    /**
     * 登录
     */
    public SysUser login(String username, String password){
        return new SysUser();
    }

}
