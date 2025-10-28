package com.project.havebin.trashcan.adapter.in.web.response;

public record TrashCanInfoResDto(
        String nickname,
        String profileImagePath,
        String roadAddress,
        String detailAddress,
        String categories
) {
}
