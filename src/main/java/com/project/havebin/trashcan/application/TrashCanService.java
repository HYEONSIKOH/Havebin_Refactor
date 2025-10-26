package com.project.havebin.trashcan.application;

import com.project.havebin.trashcan.adapter.TrashCanRepositoryAdapter;
import com.project.havebin.trashcan.adapter.in.web.response.GetAllTrashCanPositionResDto;
import com.project.havebin.trashcan.application.in.TrashCanUseCase;
import com.project.havebin.trashcan.application.out.TrashCanRepositoryPort;
import com.project.havebin.trashcan.application.out.dto.TrashCanAllPositionQueryDto;
import com.project.havebin.trashcan.domain.entity.TrashCan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TrashCanService implements TrashCanUseCase {
    private final TrashCanRepositoryPort repositoryPort;

    @Override
    public List<GetAllTrashCanPositionResDto> getAllTrashCans() {
        List<TrashCanAllPositionQueryDto> positions
                = repositoryPort.findAllPositions().orElseThrow(
                        () -> new RuntimeException("No trash cans found")
        );

        return positions.stream()
                .map(dto -> new GetAllTrashCanPositionResDto(
                        dto.id(),
                        dto.roadviewImagePath(),
                        dto.latitude(),
                        dto.longitude()
                ))
                .toList();
    }
}
