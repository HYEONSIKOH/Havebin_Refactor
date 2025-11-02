package com.project.havebin.trashcan.domain.vo;

public record RoadviewImagePath(String value) {
    public RoadviewImagePath() { this(null); }

    public RoadviewImagePath {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Roadview image is blank.");
        }
    }
}
