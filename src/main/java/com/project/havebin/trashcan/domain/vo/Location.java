package com.project.havebin.trashcan.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Location {
    @Column(name = "longitude", nullable = false)
    private double latitude;
    @Column(name = "longitude", nullable = false)
    private double longitude;

    public Location() {
        throw new IllegalArgumentException("location information blank");
    }

    public Location(double latitude, double longitude) {
        if (latitude < 33.0 || latitude > 39.0 || longitude < 124.0 || longitude > 132.0)
            throw new IllegalArgumentException("Not in South Korea");

        this.latitude = latitude;
        this.longitude = longitude;
    }
}
