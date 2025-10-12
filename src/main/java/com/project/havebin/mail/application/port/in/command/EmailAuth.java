package com.project.havebin.mail.application.port.in.command;

import com.project.havebin.user.domain.vo.Email;

public record EmailAuth(Email email, String code) {
}
