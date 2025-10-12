package com.project.havebin.mail.application.port.in;

import com.project.havebin.mail.application.port.in.command.DuplicateMail;
import com.project.havebin.mail.application.port.in.command.EmailAuth;

public interface MailUseCase {
    void duplicateMail(DuplicateMail command);
    void emailAuth(EmailAuth command);
}
