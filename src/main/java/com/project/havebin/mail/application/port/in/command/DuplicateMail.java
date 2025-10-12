package com.project.havebin.mail.application.port.in.command;

import com.project.havebin.user.domain.vo.Email;
import lombok.Builder;

@Builder
public record DuplicateMail(Email email) {
}
