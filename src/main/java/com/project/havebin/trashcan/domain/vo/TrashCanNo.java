package com.project.havebin.trashcan.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@Embeddable
@EqualsAndHashCode(of = "value")
public class TrashCanNo {
    @Column(name = "external_id", unique = true)
    private String value;

    public TrashCanNo() {
        this.value = UUID.randomUUID().toString();
    }

    public TrashCanNo(String value) {
        if (value == null || value.isBlank()) {
            value = UUID.randomUUID().toString();
        }

        this.value = value;
    }
}
