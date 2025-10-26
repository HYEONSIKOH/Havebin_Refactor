package com.project.havebin.trashcan.adapter.out.persistence.repository;

import com.project.havebin.trashcan.adapter.out.persistence.entity.TrashCanJpaEntity;
import com.project.havebin.trashcan.application.out.dto.TrashCanAllPositionQueryDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TrashCanCustomRepositoryImpl implements TrashCanCustomRepository {
    private final List<TrashCanJpaEntity> trashCanTable = new ArrayList<>();

    @Override
    public TrashCanJpaEntity save(TrashCanJpaEntity trashCanJpaEntity) {
        boolean isContains = false;
        for (TrashCanJpaEntity trashCanJpa : trashCanTable) {
            if (trashCanJpa.getId().equals(trashCanJpaEntity.getId())) {
                isContains = true;
                break;
            }
        }

        if (trashCanJpaEntity.getId() == null) {
            Long newId = (long) (trashCanTable.size() + 1);
            trashCanJpaEntity.setPK(newId);
            trashCanTable.add(trashCanJpaEntity);

            return trashCanJpaEntity;
        } else if (!isContains){
            trashCanTable.add(trashCanJpaEntity);

            return trashCanJpaEntity;
        } else {
            for (int i = 0; i < trashCanTable.size(); i++) {
                if (trashCanTable.get(i).getId().equals(trashCanJpaEntity.getId())) {
                    trashCanTable.set(i, trashCanJpaEntity);
                }
            }

            return trashCanJpaEntity;
        }
    }

    @Override
    public Optional<List<TrashCanAllPositionQueryDto>> findAllPositions() {
        List<TrashCanAllPositionQueryDto> positions = new ArrayList<>();
        for (TrashCanJpaEntity trashCan : trashCanTable) {
            positions.add(
                    new TrashCanAllPositionQueryDto(
                            trashCan.getId(), trashCan.getRoadviewImgpath(), trashCan.getLatitude(), trashCan.getLongitude()
                    )
            );
        }

        if (positions.isEmpty()) { return Optional.empty(); }

        return Optional.of(positions);
    }

    @Override
    public List<TrashCanJpaEntity> findAll() {
        return trashCanTable;
    }
}
