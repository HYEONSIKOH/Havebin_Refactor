package com.project.havebin.trashcan.adapter.out.persistence.entity;

import com.project.havebin.trashcan.domain.enums.Categories;
import com.project.havebin.trashcan.domain.enums.State;
import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "trashcan")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrashCanJpaEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(name = "image_file_name", nullable = false)
    private String imageFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity findUser;

    // 발견 날짜 (YOLO는 0301)
    @Column(nullable = false)
    private String date;

    // 우편번호
    @Column(name = "postal_code", length = 5)
    private String postalCode;

    // 도로명 주소 (주소는 없어도 가능)
    @Column(name = "road_address")
    private String roadAddress;

    // 세부주소 (예, 버스정거장, 택시승강장, 공원 등)
    @Column(name = "detail_address")
    private String detailAddress;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categories categories;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Builder
    public TrashCanJpaEntity(Long id, double latitude, double longitude, String imageFileName, UserJpaEntity findUser,
                             String date, String postalCode, String roadAddress, String detailAddress,
                             Categories categories, State state) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageFileName = imageFileName;
        this.findUser = findUser;
        this.date = date;
        this.postalCode = postalCode;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.categories = categories;
        this.state = state;
    }

    public void setPK(Long id) { this.id = id; }
}
