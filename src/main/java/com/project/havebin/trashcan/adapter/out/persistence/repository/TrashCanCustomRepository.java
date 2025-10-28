package com.project.havebin.trashcan.adapter.out.persistence.repository;

import com.project.havebin.trashcan.adapter.out.persistence.entity.TrashCanJpaEntity;
import com.project.havebin.trashcan.application.out.dto.TrashCanAllPositionQueryDto;
import com.project.havebin.trashcan.application.out.dto.TrashCanInfoQueryDto;

import java.util.List;
import java.util.Optional;

public interface TrashCanCustomRepository {
    List<TrashCanJpaEntity> findAll();
    Optional<List<TrashCanAllPositionQueryDto>> findAllPositions();
    Optional<TrashCanInfoQueryDto> findTrashCanInfoById(Long id);

    TrashCanJpaEntity save(TrashCanJpaEntity trashCanJpaEntity);
}
