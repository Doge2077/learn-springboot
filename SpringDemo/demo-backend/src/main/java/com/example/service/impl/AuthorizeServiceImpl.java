package com.example.service.impl;

import com.example.entity.Account;
import com.example.mapper.UserMapper;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.example.mytools.CheckTools.checkUsername;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {
    @Resource
    UserMapper userMapper;

    @Resource
    MailSender mailSender;

    @Value("${spring.mail.username}")
    String from;

    @Resource
    StringRedisTemplate stringRedisTemplate;

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

    @Override
    public boolean sendValidateEmail(String email, String sessionId) {
        // 用于验证
        String redisKey = "email:" + sessionId + ":" + email;
        // 取出 redisKey 验证剩余时间，防止重复发送验证码
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(redisKey))) {
            Long expireTime = Optional.ofNullable(stringRedisTemplate.getExpire(redisKey, TimeUnit.SECONDS)).orElse(0L);
            if (expireTime > 120) return false;
        }
        SecureRandom secureRandom = new SecureRandom();
        int validCode = secureRandom.nextInt(900000) + 100000;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("您的邮件");
        simpleMailMessage.setText("您的验证码是：" + validCode);
        try {
            mailSender.send(simpleMailMessage);
            // 将验证信息存入数据库，过期时间 3 分钟
            stringRedisTemplate.opsForValue().set(redisKey, String.valueOf(validCode), 3, TimeUnit.MINUTES);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
