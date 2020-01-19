package com.auth.demo.controller;

import com.auth.demo.domain.ResponseUserToken;
import com.auth.demo.domain.Role;
import com.auth.demo.domain.User;
import com.auth.demo.domain.UserDetail;
import com.auth.demo.enums.ResultCode;
import com.auth.demo.model.ResultJson;
import com.auth.demo.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 认证控制层
 * @author Chunming_Wang
 */
@RestController
@Api(description = "登陆注册及刷新token", tags = "auth-manager")
@RequestMapping("/api/v1")
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/sign")
    @ApiOperation(value = "用户注册", position = 0)
    public ResultJson sign(@Valid @RequestBody User user) {
        if (StringUtils.isAnyBlank(user.getName(), user.getPassword())) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        UserDetail userDetail = new UserDetail(user.getName(), user.getPassword(), Role.builder().id(1L).build());
        return ResultJson.success(authService.register(userDetail));
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号", position = 1)
    public ResultJson<ResponseUserToken> login(@Valid @RequestBody User user){
        final ResponseUserToken response = authService.login(user.getName(), user.getPassword());
        return ResultJson.success(response);
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "登出", notes = "退出登陆", position = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    })
    public ResultJson logout(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        authService.logout(token);
        return ResultJson.success();
    }

    @GetMapping(value = "/token")
    @ApiOperation(value = "根据用户名获取token", notes = "根据用户名获取token", position = 3)
    public ResultJson getToken(@RequestParam(value = "userName") String userName){
        String token = authService.getTokenByUserName(userName);
        return ResultJson.success(token);
    }

    @GetMapping(value = "/user")
    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息", position = 4)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    })
    public ResultJson getUser(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        return ResultJson.success(userDetail);
    }

    /*@GetMapping(value = "/refresh")
    @ApiOperation(value = "刷新token")
    public ResultJson refreshAndGetAuthenticationToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        ResponseUserToken response = authService.refresh(token);
        if(response == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST, "token无效");
        } else {
            return ResultJson.success(response);
        }
    }*/

}
