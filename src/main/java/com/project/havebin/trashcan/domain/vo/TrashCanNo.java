package com.project.havebin.trashcan.domain.vo;

import io.hypersistence.tsid.TSID;

public record TrashCanNo(Long value) {
    public TrashCanNo() {
        this(TSID.Factory.getTsid().toLong());
    }

    public TrashCanNo {
        if (value == null) {
            throw new IllegalArgumentException("TrashCan ID is blank.");
        }
    }
}
