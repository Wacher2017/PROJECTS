package com.auth.demo.config;

import com.auth.demo.domain.UserDetail;
import com.auth.demo.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chunming_Wang
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String token_header;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        String auth_token = request.getHeader(this.token_header);
        if (StringUtils.isNotEmpty(auth_token) && auth_token.startsWith(this.tokenHead)) {
            auth_token = auth_token.substring(this.tokenHead.length());
        } else {
            // 不按规范,不允许通过验证
            auth_token = null;
        }

        String username = jwtUtils.getUsernameFromToken(auth_token);

        logger.info(String.format("Checking authentication for userDetail %s.", username));

        if (username != null && jwtUtils.containToken(username, auth_token)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetail userDetail = jwtUtils.getUserFromToken(auth_token);
            if (jwtUtils.validateToken(auth_token, userDetail)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetail, null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info(String.format("Authenticated userDetail %s, setting security context", username));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
