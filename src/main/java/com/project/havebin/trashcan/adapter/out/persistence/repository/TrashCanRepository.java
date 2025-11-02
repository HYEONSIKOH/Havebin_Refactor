package com.project.havebin.trashcan.adapter.out.persistence.repository;

import com.project.havebin.trashcan.adapter.out.persistence.entity.TrashCanJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrashCanRepository extends JpaRepository<TrashCanJpaEntity, Long> {
}
