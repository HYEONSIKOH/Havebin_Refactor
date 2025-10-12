package com.project.havebin.mail.adapter.in.web.dto.request;

import com.project.havebin.mail.application.port.in.command.DuplicateMail;
import com.project.havebin.mail.application.port.in.command.EmailAuth;
import com.project.havebin.user.domain.vo.Email;

public record EmailAuthReqDto(String email, String code) {
    public EmailAuth toCommand() {
        return new EmailAuth(new Email(email), code);
    }
}
