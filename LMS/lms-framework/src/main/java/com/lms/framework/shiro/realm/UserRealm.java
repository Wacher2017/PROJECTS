package com.lms.framework.shiro.realm;

import com.lms.common.exception.user.*;
import com.lms.framework.shiro.service.SysLoginService;
import com.lms.framework.utils.ShiroUtils;
import com.lms.system.domain.SysUser;
import com.lms.system.service.ISysMenuService;
import com.lms.system.service.ISysRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 自定义Realm 处理登录 权限
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SysUser user = ShiroUtils.getSysUser();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            // 角色列表
            Set<String> roles = roleService.selectRoleKeys(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 功能列表
            Set<String> menus = menuService.selectPermsByUserId(user.getUserId());
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }
        SysUser user;
        try {
            user = loginService.login(username, password);
        } catch (CaptchaException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException | RoleBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

}
