package com.lms.api.system;

import com.lms.common.api.ResultVO;
import com.lms.common.constants.Constants;
import com.lms.common.core.controller.BaseController;
import com.lms.common.utils.ServletUtils;
import com.lms.framework.security.LoginBody;
import com.lms.framework.security.LoginUser;
import com.lms.framework.security.service.SysLoginService;
import com.lms.framework.security.service.SysPermissionService;
import com.lms.framework.security.service.TokenService;
import com.lms.system.domain.SysMenu;
import com.lms.system.domain.SysUser;
import com.lms.system.pojo.RouterVo;
import com.lms.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 登录验证
 */
@RestController
public class SysLoginController extends BaseController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登陆信息
     * @return 结果
     */
    @PostMapping("/login")
    public ResultVO<Map<String, Object>> login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.TOKEN, token);
        return new ResultVO<>(map);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public ResultVO<Map<String, Object>> getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roles", roles);
        map.put("permissions", permissions);
        return new ResultVO<>(map);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public ResultVO<List<RouterVo>> getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return new ResultVO<>(menuService.buildMenus(menus));
    }

}
