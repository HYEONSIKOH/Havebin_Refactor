package com.project.havebin.trashcan.adapter.in.web;

import com.project.havebin.trashcan.adapter.in.web.response.AllTrashCanPositionResDto;
import com.project.havebin.trashcan.adapter.in.web.response.TrashCanInfoResDto;
import com.project.havebin.trashcan.application.in.TrashCanUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/trashcan")
@Tag(name = "TrashCan API", description = "쓰레기통 관련 API")
public class TrashCanController {
    private final TrashCanUseCase trashCanUseCase;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTrashCans() {
        List<AllTrashCanPositionResDto> response = trashCanUseCase.getAllTrashCans();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{trashCanId}")
    public ResponseEntity<?> getTrashCanInfo(@PathVariable Long trashCanId) {
        TrashCanInfoResDto response = trashCanUseCase.getTrashCanInfoById(trashCanId);
        return ResponseEntity.ok(response);
    }
}
