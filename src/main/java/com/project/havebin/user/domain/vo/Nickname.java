package com.project.havebin.user.domain.vo;

public record Nickname(String value) {
    public Nickname() { this(null); }

    public Nickname {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Nickname blank");
        }

        if (value.length() < 2 || value.length() > 10) {
            throw new IllegalArgumentException("Invalid email format");
        }

    }
}
