package com.project.havebin.trashcan.adapter.out.persistence.repository;

import com.project.havebin.trashcan.adapter.out.persistence.entity.TrashCanJpaEntity;
import com.project.havebin.trashcan.application.out.dto.TrashCanAllPositionQueryDto;
import com.project.havebin.trashcan.application.out.dto.TrashCanInfoQueryDto;
import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;
import com.project.havebin.user.adapter.out.persistence.repository.UserCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class TrashCanCustomRepositoryImpl implements TrashCanCustomRepository {
    private final List<TrashCanJpaEntity> trashCanTable = new ArrayList<>();

    private final UserCustomRepository userRepository;

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
    public Optional<TrashCanInfoQueryDto> findTrashCanInfoById(Long id) {
        for (TrashCanJpaEntity trashCan : trashCanTable) {
            if (trashCan.getId().equals(id)) {
                // 없으면 "Unknown User"
                UserJpaEntity finduser = userRepository.findById(trashCan.getFindUser().getId())
                        .orElseGet(null);

                TrashCanInfoQueryDto infoDto = new TrashCanInfoQueryDto(
                        finduser == null ? "Unknown User" : finduser.getNickname(),
                        trashCan.getRoadviewImgpath(),
                        trashCan.getAddress(),
                        trashCan.getDetailAddress(),
                        trashCan.getCategories().name()
                );

                return Optional.of(infoDto);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<TrashCanJpaEntity> findAll() {
        return trashCanTable;
    }
}
