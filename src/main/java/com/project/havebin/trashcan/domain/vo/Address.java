package com.project.havebin.trashcan.domain.vo;

/**
 * @param postalCode    우편번호
 * @param roadAddress   도로명 주소
 * @param detailAddress 상세 주소 (예, 버스정거장, 택시승강장, 공원 등)
 */
public record Address(String postalCode, String roadAddress, String detailAddress) {
    public Address() {
        this(null, null, null);
    }

    public Address {
        if (roadAddress == null || roadAddress.isBlank()) {
            throw new IllegalArgumentException("RoadAddress blank");
        }

        if (detailAddress == null || detailAddress.isBlank()) {
            throw new IllegalArgumentException("DetailAddress blank");
        }

        if (postalCode == null || postalCode.isBlank()) {
            postalCode = "";
        }
    }
}
