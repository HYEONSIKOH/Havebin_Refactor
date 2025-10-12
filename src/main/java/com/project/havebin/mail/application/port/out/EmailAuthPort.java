package com.project.havebin.mail.application.port.out;

import com.project.havebin.user.domain.vo.Email;

public interface EmailAuthPort {
    void saveEmailAuthCode(Email email, String code);
    boolean checkEmailAuthCode(Email email, String code);
}
