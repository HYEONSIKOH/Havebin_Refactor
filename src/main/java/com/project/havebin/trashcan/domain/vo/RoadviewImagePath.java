package com.project.havebin.trashcan.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class RoadviewImagePath {
    @Column(name = "roadviewImgpath", nullable = false)
    private final String value;

    public RoadviewImagePath() { throw new IllegalArgumentException("Roadview link blank"); }

    public RoadviewImagePath(String value) {
        this.value = value;
    }
}
