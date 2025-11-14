package com.project.havebin.report.adapter.out.psersistence.entity;

import com.project.havebin.report.adapter.out.psersistence.entity.enums.ReportCategory;
import com.project.havebin.trashcan.adapter.out.persistence.entity.TrashCanJpaEntity;
import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportTrashcan {
    @Id @Tsid
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user", nullable = false)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="trashcan", nullable = false)
    private TrashCanJpaEntity trashcan;

    @Enumerated(EnumType.ORDINAL)
    private ReportCategory reportCategory;

    // false: 처리중(빨간색글씨)
    // true: 처리완료(초록색글씨)
    @Column(nullable = false)
    private boolean modifyStatus;

    public ReportTrashcan (UserJpaEntity user, TrashCanJpaEntity trashcan, ReportCategory reportCategory, boolean modifyStatus) {
        this.user = user;
        this.trashcan = trashcan;
        this.reportCategory = reportCategory;
        this.modifyStatus = modifyStatus;
    }
}
