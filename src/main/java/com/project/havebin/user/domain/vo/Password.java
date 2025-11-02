package com.project.havebin.user.domain.vo;

public record Password(String value) {
    public Password() { this(null); }

    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password blank");
        }

        if (value.length() < 8 || value.length() > 20) {
            throw new IllegalArgumentException("Password must be between 8 and 20 characters");
        }
    }
}
