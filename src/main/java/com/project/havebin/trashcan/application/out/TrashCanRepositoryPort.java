package com.project.havebin.trashcan.application.out;

import com.project.havebin.trashcan.application.out.dto.TrashCanAllPositionQueryDto;
import com.project.havebin.trashcan.domain.entity.TrashCan;

import java.util.*;

public interface TrashCanRepositoryPort {
    List<TrashCan> findAll();
    Optional<List<TrashCanAllPositionQueryDto>> findAllPositions();
}
