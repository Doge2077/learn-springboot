package com.example.service;

import com.example.entity.Account;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.mytools.CheckTools.checkUsername;

@Service
public class AuthorizeService implements UserDetailsService {
    // 登录权限校验服务
    @Resource
    UserMapper userMapper;

    // 获取用户登录信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || !checkUsername(username)) {
            throw new UsernameNotFoundException("用户名非法");
        }
        Account account = userMapper.getAccountByUsernameOrEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("user")
                .build();
    }
}
