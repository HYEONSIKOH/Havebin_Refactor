package com.project.havebin.trashcan.domain.vo;

import io.hypersistence.tsid.TSID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "value")
public class TrashCanNo {
    private final Long value;

    public TrashCanNo() { this.value = TSID.Factory.getTsid().toLong(); }

    public TrashCanNo(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("TrashCan ID is blank.");
        }

        this.value = value;
    }
}
