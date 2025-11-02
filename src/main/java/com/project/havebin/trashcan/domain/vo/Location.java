package com.project.havebin.trashcan.domain.vo;

public record Location(double latitude, double longitude) {
    public Location() {
        this(Double.NaN, Double.NaN);
    }

    public Location {
        if (Double.isNaN(latitude) || Double.isNaN(longitude)) {
            throw new IllegalArgumentException("location information blank");
        }

        if (latitude < 33.0 || latitude > 39.0 || longitude < 124.0 || longitude > 132.0)
            throw new IllegalArgumentException("Not in South Korea");

    }
}
