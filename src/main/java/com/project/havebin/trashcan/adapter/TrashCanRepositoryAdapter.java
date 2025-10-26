package com.project.havebin.trashcan.adapter;

import com.project.havebin.trashcan.adapter.out.persistence.entity.TrashCanJpaEntity;
import com.project.havebin.trashcan.adapter.out.persistence.mapper.TrashCanMapper;
import com.project.havebin.trashcan.adapter.out.persistence.repository.TrashCanCustomRepository;
import com.project.havebin.trashcan.application.out.TrashCanRepositoryPort;
import com.project.havebin.trashcan.application.out.dto.TrashCanAllPositionQueryDto;
import com.project.havebin.trashcan.domain.entity.TrashCan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TrashCanRepositoryAdapter implements TrashCanRepositoryPort {
    private final TrashCanCustomRepository repository;

    @Override
    public List<TrashCan> findAll() {
        List<TrashCanJpaEntity> trashCanJpaEntities = repository.findAll();

        List<TrashCan> trashCans = new ArrayList<>();
        for (TrashCanJpaEntity t : trashCanJpaEntities) {
            trashCans.add(TrashCanMapper.toDomain(t));
        }

        return trashCans;
    }

    @Override
    public Optional<List<TrashCanAllPositionQueryDto>> findAllPositions() {
        return repository.findAllPositions();
    }

//    @Override
//    public TrashCan save(TrashCan trashCan) {
//        TrashCanJpaEntity trashCanJpaEntity = UserMapper.toJpa(user);
//
//        return UserMapper.toDomain(repository.save(trashCanJpaEntity));
//    }
}
