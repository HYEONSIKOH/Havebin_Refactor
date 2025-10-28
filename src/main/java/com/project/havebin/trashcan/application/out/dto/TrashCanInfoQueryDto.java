package com.project.havebin.trashcan.application.out.dto;

public record TrashCanInfoQueryDto(
        String nickname,
        String profileImagePath,
        String roadAddress,
        String detailAddress,
        String categories
) {
}
