package com.auth.demo.config;

import com.auth.demo.enums.ResultCode;
import com.auth.demo.model.ResultJson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author Chunming_Wang
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    /**
     * 验证为未登陆状态会进入此方法，认证错误
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        System.out.println("认证失败：" + authException.getMessage());
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String body = ResultJson.failure(ResultCode.UNAUTHORIZED, authException.getMessage()).toString();
        printWriter.write(body);
        printWriter.flush();
    }

}
