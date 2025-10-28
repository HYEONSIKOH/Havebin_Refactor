package com.project.havebin.trashcan.adapter.out.persistence.mapper;

import com.project.havebin.trashcan.adapter.out.persistence.entity.TrashCanJpaEntity;
import com.project.havebin.trashcan.domain.entity.TrashCan;
import com.project.havebin.trashcan.domain.vo.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TrashCanMapper {
    // 도메인 → JPA (신규 생성용)
    public TrashCanJpaEntity toJpa(TrashCan domain) {
        if (domain == null) { return null; }

        return TrashCanJpaEntity.builder()
                .id(domain.getId().getValue())
                .latitude(domain.getLocation().getLatitude())
                .longitude(domain.getLocation().getLongitude())
                .roadviewImgpath(domain.getRoadviewImagePath().getValue())
                .findUser(null)
                .address(domain.getAddress().getRoadAddress())
                .detailAddress(domain.getAddress().getDetailAddress())
                .state(domain.getState())
                .categories(domain.getCategories())
                .date(domain.getFindDate().getValue())
                .build();
    }

    // JPA → 도메인 (복원용)
    public static TrashCan toDomain(TrashCanJpaEntity e) {
        if (e == null) { return null; }

        return TrashCan.builder()
                .id(new TrashCanNo(e.getId()))
                .location(new Location(e.getLatitude(), e.getLongitude()))
                .roadviewImagePath(new RoadviewImagePath(e.getRoadviewImgpath()))
                .findUser(null)
                .address(new Address(null, e.getAddress(), e.getDetailAddress()))
                .categories(e.getCategories())
                .state(e.getState())
                .findDate(new FindDate(e.getDate()))
                .build();

    }
}
