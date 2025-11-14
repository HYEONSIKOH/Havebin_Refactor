package com.project.havebin.report.adapter.in.web;

import com.project.havebin.report.application.ReportTrashcanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report/trashcan")
@Tag(name = "Report Trashcan API", description = "쓰레기통 신고 관련 API")
public class ReportTrashcanController {
    private final ReportTrashcanService reportTrashcanService;

    @GetMapping("")
    public ResponseEntity<?> findTrashcanReportCount(@RequestParam(required = false) Long trashcan) {
        int count = reportTrashcanService.getTrashcanReportCount(trashcan);
        return ResponseEntity.ok(Map.of("reportCount", count));
    }
}
