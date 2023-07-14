package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class DemoBackendApplicationTests {

    @Test
    void contextLoads() {
        Argon2PasswordEncoder encoder1 = new Argon2PasswordEncoder(16, 64, 2, 65536, 10);
        BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder();
        System.out.println(encoder2.encode("123456"));
    }

}
