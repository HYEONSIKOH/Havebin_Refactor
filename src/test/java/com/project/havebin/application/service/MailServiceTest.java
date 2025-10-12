package com.project.havebin.application.service;

import com.project.havebin.user.domain.vo.Email;
import com.project.havebin.user.application.port.out.UserRepositoryPort;
import com.project.havebin.mail.application.MailService;
import com.project.havebin.mail.application.port.in.command.*;
import com.project.havebin.mail.application.port.out.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("MailService 테스트")
@ExtendWith(SpringExtension.class)
public class MailServiceTest {
    @InjectMocks
    private MailService mailService;

    @Mock private UserRepositoryPort userRepositoryPort;
    @Mock private EmailAuthPort emailAuthPort;
    @Mock private MailSenderPort mailSenderPort;

    @Test
    @DisplayName("이메일 중복 확인 - 중복이 없는 경우")
    public void duplicateMail_noDuplicate() {
        // given
        DuplicateMail command = DuplicateMail.builder()
                .email(new Email("test@example.com"))
                .build();

        when(userRepositoryPort.duplicateEmail(command.email())).thenReturn(false);
        when(mailSenderPort.sendMessage(eq(command.email()), anyString())).thenReturn(true);

        // when
        mailService.duplicateMail(command);

        // then
        verify(userRepositoryPort, times(1)).duplicateEmail(command.email());
        verify(mailSenderPort, times(1)).sendMessage(eq(command.email()), anyString());
        verify(emailAuthPort, times(1)).saveEmailAuthCode(eq(command.email()), anyString());
    }

    @Test
    @DisplayName("이메일 중복 확인 - 중복 이메일")
    public void duplicateMail_duplicate() {
        // given
        DuplicateMail command = DuplicateMail.builder()
                .email(new Email("test@example.com"))
                .build();

        when(userRepositoryPort.duplicateEmail(command.email())).thenReturn(true);

        // when
        assertThatThrownBy(() -> mailService.duplicateMail(command))
                .isInstanceOf(RuntimeException.class);

        // then
        verify(userRepositoryPort, times(1)).duplicateEmail(command.email());
        verify(mailSenderPort, never()).sendMessage(any(Email.class), anyString());
        verify(emailAuthPort, never()).saveEmailAuthCode(any(Email.class), anyString());
    }

    @Test
    @DisplayName("이메일 인증번호 - 인증번호가 맞을 때")
    public void checkEmailAuthCode_correctCode() {
        // given
        Email email = new Email("test@example.com");
        String code = "1234";

        when(emailAuthPort.checkEmailAuthCode(email, code)).thenReturn(true);

        // when
        mailService.emailAuth(new EmailAuth(email, code));

        // then
        verify(emailAuthPort, times(1)).checkEmailAuthCode(email, code);
    }

    @Test
    @DisplayName("이메일 인증번호 - 인증번호가 틀릴 때")
    public void checkEmailAuthCode_incorrectCode() {
        // given
        Email email = new Email("test@example.com");
        String code = "1234";

        when(emailAuthPort.checkEmailAuthCode(email, code)).thenReturn(false);

        // when
        assertThatThrownBy(() -> mailService.emailAuth(new EmailAuth(email, code)))
                .isInstanceOf(RuntimeException.class);

        // then
        verify(emailAuthPort, times(1)).checkEmailAuthCode(email, code);
    }
}
