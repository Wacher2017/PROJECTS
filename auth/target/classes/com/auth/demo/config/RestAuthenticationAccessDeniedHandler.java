package com.auth.demo.config;

import com.auth.demo.enums.ResultCode;
import com.auth.demo.model.ResultJson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Chunming_Wang
 */
@Component("RestAuthenticationAccessDeniedHandler")
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 登陆状态下，权限不足执行该方法
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        System.out.println("权限不足：" + e.getMessage());
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String body = ResultJson.failure(ResultCode.FORBIDDEN, e.getMessage()).toString();
        printWriter.write(body);
        printWriter.flush();
    }

}
