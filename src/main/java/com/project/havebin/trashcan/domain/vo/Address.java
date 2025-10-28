package com.project.havebin.trashcan.domain.vo;

import lombok.Getter;

@Getter
public class Address {
    private final String postalCode;     // 우편번호
    private final String roadAddress;    // 도로명 주소
    private final String detailAddress;  // 상세 주소 (예, 버스정거장, 택시승강장, 공원 등)

    public Address() {
        throw new IllegalArgumentException("Address blank");
    }

    public Address(String postalCode, String roadAddress, String detailAddress) {
        this.postalCode = postalCode;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }
}
