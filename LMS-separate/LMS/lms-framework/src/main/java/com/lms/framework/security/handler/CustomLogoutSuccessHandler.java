package com.lms.framework.security.handler;

import com.alibaba.fastjson.JSON;
import com.lms.common.api.ResultCode;
import com.lms.common.api.ResultVO;
import com.lms.common.constants.Constants;
import com.lms.common.utils.ObjectUtils;
import com.lms.common.utils.ServletUtils;
import com.lms.framework.manager.AsyncManager;
import com.lms.framework.manager.factory.AsyncFactory;
import com.lms.framework.security.LoginUser;
import com.lms.framework.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 */
@Configuration
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (ObjectUtils.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(new ResultVO<>(ResultCode.SUCCESS, "退出成功", null)));
    }

}
