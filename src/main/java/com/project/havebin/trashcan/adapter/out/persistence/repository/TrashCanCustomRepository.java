package com.project.havebin.trashcan.adapter.out.persistence.repository;

import com.project.havebin.trashcan.adapter.out.persistence.entity.TrashCanJpaEntity;
import com.project.havebin.trashcan.application.out.dto.TrashCanAllPositionQueryDto;

import java.util.List;
import java.util.Optional;

public interface TrashCanCustomRepository {
    List<TrashCanJpaEntity> findAll();
    Optional<List<TrashCanAllPositionQueryDto>> findAllPositions();

    TrashCanJpaEntity save(TrashCanJpaEntity trashCanJpaEntity);
}
