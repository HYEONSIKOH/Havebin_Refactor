package com.project.havebin.report.application;

import com.project.havebin.report.adapter.out.psersistence.repository.ReportTrashcanRepository;
import com.project.havebin.report.application.in.ReportTrashcanUseCase;
import com.project.havebin.report.application.out.ReportTrashcanRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportTrashcanService implements ReportTrashcanUseCase {
    private final ReportTrashcanRepositoryPort repositoryPort;

    public int getTrashcanReportCount(Long trashcanId) {
        return repositoryPort.findTrashcanReportCount(trashcanId);
    }
}
