package com.project.havebin.report.adapter.out.psersistence.repository;

import com.project.havebin.report.adapter.out.psersistence.entity.ReportTrashcan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportTrashcanRepository extends JpaRepository<ReportTrashcan, Long>, ReportTrashcanRepositoryCustom {
}
