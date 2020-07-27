package com.lms.framework.security.handler;

import com.alibaba.fastjson.JSON;
import com.lms.common.api.ResultCode;
import com.lms.common.api.ResultVO;
import com.lms.common.utils.ServletUtils;
import com.lms.common.utils.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 */
@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 4893464278250323596L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(new ResultVO<>(ResultCode.UNAUTHORIZED, msg, null)));
    }

}
