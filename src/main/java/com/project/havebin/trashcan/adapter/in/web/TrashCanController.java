package com.project.havebin.trashcan.adapter.in.web;

import com.project.havebin.trashcan.adapter.in.web.response.GetAllTrashCanPositionResDto;
import com.project.havebin.trashcan.application.in.TrashCanUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/trashcan")
@Tag(name = "TrashCan API", description = "쓰레기통 관련 API")
public class TrashCanController {
    private final TrashCanUseCase trashCanUseCase;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTrashCans() {
        List<GetAllTrashCanPositionResDto> response = trashCanUseCase.getAllTrashCans();
        return ResponseEntity.ok(response);
    }

    //===================== [예외 처리] =====================

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "message", ex.getMessage(),
                        "status", 400
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "message", ex.getMessage(),
                        "status", 401
                ));
    }
}
