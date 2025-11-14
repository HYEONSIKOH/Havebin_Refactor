package com.project.havebin.report.adapter.out.psersistence.repository;

import com.project.havebin.report.adapter.out.psersistence.entity.QReportTrashcan;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportTrashcanRepositoryImpl implements ReportTrashcanRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int countByTrashcanId(Long trashcanId) {
        /*
        SELECT count(*)
        FROM ReportTrashcan
        WHERE trashcan = trashcanId AND modifyStatus = FALSE;
         */

        Long result = jpaQueryFactory
                .select(QReportTrashcan.reportTrashcan.count())
                .from(QReportTrashcan.reportTrashcan)
                .where(
                        QReportTrashcan.reportTrashcan.trashcan.id.eq(trashcanId),
                        QReportTrashcan.reportTrashcan.modifyStatus.eq(false)
                )
                .fetchFirst();

        return result == null ? 0 : result.intValue();
    }
}
