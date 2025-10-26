package com.project.havebin.trashcan.adapter.in.web.response;

public record GetAllTrashCanPositionResDto(
        Long trashCanId,
        String roadviewImgpath,
        double latitude,
        double longitude) {
}
