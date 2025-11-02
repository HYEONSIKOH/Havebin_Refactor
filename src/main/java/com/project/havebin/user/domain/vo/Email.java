package com.project.havebin.user.domain.vo;

public record Email(String value) {
    public Email() { this(null); }

    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("email blank");
        }

        String v = value.trim();
        String REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!v.matches(REGEX)) {
            throw new IllegalArgumentException("Invalid email format");
        }

    }
}
