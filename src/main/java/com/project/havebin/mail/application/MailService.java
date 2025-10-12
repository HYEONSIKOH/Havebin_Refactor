package com.project.havebin.mail.application;

import com.project.havebin.mail.application.port.in.MailUseCase;
import com.project.havebin.mail.application.port.in.command.DuplicateMail;
import com.project.havebin.mail.application.port.in.command.EmailAuth;
import com.project.havebin.mail.application.port.out.EmailAuthPort;
import com.project.havebin.mail.application.port.out.MailSenderPort;
import com.project.havebin.user.application.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class MailService implements MailUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final MailSenderPort mailSenderPort;
    private final EmailAuthPort emailAuthPort;

    private final static int CODE_LENGTH = 4;

    private static String createKey() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        Set<Integer> usedNumbers = new HashSet<>();

        while (code.length() < CODE_LENGTH) {
            SimpleDateFormat format1 = new SimpleDateFormat ( "ss");
            Date time = new Date();
            int time1 = Integer.parseInt(format1.format(time));

            // 0부터 9 사이의 랜덤 숫자 생성
            int randomNumber = random.nextInt(10) * time1;

            if (randomNumber >= 10) {
                randomNumber = randomNumber % (randomNumber / 10);
            }

            // 생성된 숫자가 중복되지 않도록 확인
            if (!usedNumbers.contains(randomNumber)) {
                code.append(randomNumber);
                usedNumbers.add(randomNumber);
            }
        }

        return code.toString();
    }

    @Override
    public void duplicateMail(DuplicateMail command) {
        // 중복 확인
        if (userRepositoryPort.duplicateEmail(command.email())) {
            throw new RuntimeException("duplicate");
        }

        // 인증 코드 생성 & 메일 전송
        String code = createKey();
        if (!mailSenderPort.sendMessage(command.email(), code)) {
            throw new RuntimeException("fail to send mail");
        }

        // Redis에 인증 코드 저장
        emailAuthPort.saveEmailAuthCode(command.email(), code);
    }

    @Override
    public void emailAuth(EmailAuth command) {
        if (!emailAuthPort.checkEmailAuthCode(command.email(), command.code())){
            throw new RuntimeException("timeout");
        }
    }
}
