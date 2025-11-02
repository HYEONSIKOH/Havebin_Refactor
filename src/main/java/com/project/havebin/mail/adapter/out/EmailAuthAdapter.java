package com.project.havebin.mail.adapter.out;

import com.project.havebin.mail.application.port.out.EmailAuthPort;
import com.project.havebin.user.domain.vo.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailAuthAdapter implements EmailAuthPort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void saveEmailAuthCode(Email email, String code) {
        //log.info("save email auth code: {}", code);
        redisTemplate.opsForValue().set(email.value(), code, 300, TimeUnit.SECONDS);
    }

    @Override
    public boolean checkEmailAuthCode(Email email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email.value());
        //log.info("check email auth code: {}, stored code: {}", code, storedCode);
        return code.equals(storedCode);
    }
}
