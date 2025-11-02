package com.project.havebin.user.domain.vo;

import io.hypersistence.tsid.TSID;

public record UserNo(Long value) {
    public UserNo() {
        this(TSID.Factory.getTsid().toLong());
    }

    public UserNo {
        if (value == null) {
            value = TSID.Factory.getTsid().toLong();
        }
    }
}
