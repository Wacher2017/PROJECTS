package com.auth.demo.service.impl;

import com.auth.demo.domain.Role;
import com.auth.demo.domain.UserDetail;
import com.auth.demo.mapper.AuthMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Chunming_Wang
 */
@Component(value="CustomUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthMapper authMapper;

    public UserDetailsServiceImpl(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetail userDetail = authMapper.findByUsername(name);
        if (userDetail == null) {
            throw new UsernameNotFoundException(String.format("No userDetail found with username '%s'.", name));
        }
        Role role = authMapper.findRoleByUserId(userDetail.getId());
        userDetail.setRole(role);
        return userDetail;
    }

}
