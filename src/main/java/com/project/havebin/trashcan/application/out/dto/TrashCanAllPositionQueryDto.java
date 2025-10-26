package com.project.havebin.trashcan.application.out.dto;

public record TrashCanAllPositionQueryDto(
        Long id,
        String roadviewImagePath,
        double latitude,
        double longitude
) {
}
