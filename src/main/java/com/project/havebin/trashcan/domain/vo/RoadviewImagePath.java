package com.project.havebin.trashcan.domain.vo;

import lombok.Getter;

@Getter
public class RoadviewImagePath {
    private final String value;

    public RoadviewImagePath() { throw new IllegalArgumentException("Roadview link blank"); }

    public RoadviewImagePath(String value) {
        this.value = value;
    }
}
