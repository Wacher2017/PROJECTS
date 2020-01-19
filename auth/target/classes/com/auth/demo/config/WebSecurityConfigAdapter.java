package com.auth.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Chunming_Wang
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;

    private final UserDetailsService customUserDetailsService;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    public WebSecurityConfigAdapter(@Qualifier("RestAuthenticationAccessDeniedHandler") AccessDeniedHandler accessDeniedHandler,
                                    @Qualifier("CustomUserDetailsService") UserDetailsService customUserDetailsService,
                                    JwtAuthenticationEntryPoint unauthorizedHandler,
                                    JwtAuthenticationTokenFilter authenticationTokenFilter) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.customUserDetailsService = customUserDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.authenticationTokenFilter = authenticationTokenFilter;
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.customUserDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 装载BCrypt密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // ALTOUGH THIS SEEMS LIKE USELESS CODE,
        // ITS REQUIRED TO PREVEND SPRING BOOT AUTO-CONFIGURATION
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 对于获取token的rest api要允许匿名访问
                .authorizeRequests().antMatchers(
                            "/api/v1/sign", "/api/v1/login", "/api/v1/token", "/error/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 添加JWT filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                        "/swagger-ui.html",
                        "**/swagger-ui.html",
                        "/v2/api-docs",  //swagger api json
                        "/swagger-resources/configuration/ui", //用来获取支持的动作
                        "/swagger-resources", //用来获取api-docs的URI
                        "/swagger-resources/configuration/security", //安全选项
                        "/swagger-resources/**",
                        "/webjars/springfox-swagger-ui/**",
                        "/favicon.ico",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.png",
                        "/**/*.gif",
                        "/v2/**",
                        "/**/*.ttf"
                );
    }

}
