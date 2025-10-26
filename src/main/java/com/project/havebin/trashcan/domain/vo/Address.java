package com.project.havebin.trashcan.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    @Column(name = "postal", unique = true)
    private String postalCode;     // 우편번호

    @Column(name = "address", unique = true)
    private String roadAddress;    // 도로명 주소

    @Column(name = "detailAddress", unique = true)
    private String detailAddress;  // 상세 주소 (예, 버스정거장, 택시승강장, 공원 등)

    public Address() {
        throw new IllegalArgumentException("Address blank");
    }

    public Address(String postalCode, String roadAddress, String detailAddress) {
        this.postalCode = postalCode;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }
}
