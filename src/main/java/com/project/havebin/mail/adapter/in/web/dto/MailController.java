package com.project.havebin.mail.adapter.in.web.dto;

import com.project.havebin.mail.adapter.in.web.dto.request.DuplicateMailReqDto;
import com.project.havebin.mail.adapter.in.web.dto.request.EmailAuthReqDto;
import com.project.havebin.mail.application.port.in.MailUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/mail")
@Tag(name = "Mail API", description = "메일 전송 및 인증 관련 API")
public class MailController {
    private final MailUseCase mailUseCase;

    @PostMapping("/duplicateMail")
    @Operation(summary = "메일 중복 조회 및 인증번호 전송", description = "이메일 중복 확인 & 존재하지 않는다면 인증번호 전송.")
    public ResponseEntity<?> duplicateMail(@Valid @RequestBody DuplicateMailReqDto dto) {
        mailUseCase.duplicateMail(dto.toCommand());
        return ResponseEntity.ok("success");
    }

    @PostMapping("/auth")
    @Operation(summary = "이메일 인증번호 확인")
    public ResponseEntity<?> mailCodeAuth(@Valid @RequestBody EmailAuthReqDto dto) {
        mailUseCase.emailAuth(dto.toCommand());
        return ResponseEntity.ok("success");
    }
}
