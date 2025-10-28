package com.project.havebin.trashcan.application;

import com.project.havebin.trashcan.adapter.in.web.response.AllTrashCanPositionResDto;
import com.project.havebin.trashcan.adapter.in.web.response.TrashCanInfoResDto;
import com.project.havebin.trashcan.application.in.TrashCanUseCase;
import com.project.havebin.trashcan.application.out.TrashCanRepositoryPort;
import com.project.havebin.trashcan.application.out.dto.TrashCanAllPositionQueryDto;
import com.project.havebin.trashcan.application.out.dto.TrashCanInfoQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TrashCanService implements TrashCanUseCase {
    private final TrashCanRepositoryPort repositoryPort;

    @Override
    public List<AllTrashCanPositionResDto> getAllTrashCans() {
        List<TrashCanAllPositionQueryDto> positions
                = repositoryPort.findAllPositions().orElseThrow(
                        () -> new RuntimeException("No trash cans found")
        );

        List<AllTrashCanPositionResDto> result = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            TrashCanAllPositionQueryDto dto = positions.get(i);
            AllTrashCanPositionResDto resDto = new AllTrashCanPositionResDto(
                    dto.id(),
                    dto.roadviewImagePath(),
                    dto.latitude(),
                    dto.longitude()
            );
            System.out.println(dto.id());
            result.add(resDto);
        }

        return result;
    }

    @Override
    public TrashCanInfoResDto getTrashCanInfoById(Long id) {
        TrashCanInfoQueryDto data = repositoryPort.getTrashCanInfoById(id)
                .orElseThrow(() -> new RuntimeException("Trash can not found"));

        return new TrashCanInfoResDto(
                data.nickname(),
                data.profileImagePath(),
                data.roadAddress(),
                data.detailAddress(),
                data.categories()
        );
    }
}
