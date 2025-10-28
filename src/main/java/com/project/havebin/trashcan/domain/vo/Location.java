package com.project.havebin.trashcan.domain.vo;

import lombok.Getter;

@Getter
public class Location {
    private final double latitude;
    private final double longitude;

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
