package com.project.havebin.report.adapter.out;

import com.project.havebin.report.adapter.out.psersistence.repository.ReportTrashcanRepository;
import com.project.havebin.report.application.out.ReportTrashcanRepositoryPort;
import com.project.havebin.trashcan.application.out.TrashCanRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReportTrashCanRepositoryAdapter implements ReportTrashcanRepositoryPort {
    private final ReportTrashcanRepository repository;

    @Override
    public int findTrashcanReportCount(Long trashcanId) {
        return repository.countByTrashcanId(trashcanId);
    }
}
