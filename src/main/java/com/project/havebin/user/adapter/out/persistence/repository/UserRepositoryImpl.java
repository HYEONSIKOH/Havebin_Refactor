package com.project.havebin.user.adapter.out.persistence.repository;

import com.project.havebin.user.adapter.out.persistence.entity.QUserJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean existsByNickname(String nickname) {
        /*
        SELECT 1
        FROM user_jpa_entity
        WHERE nickname = :nickname
         */
        return jpaQueryFactory
                .selectOne()
                .from(QUserJpaEntity.userJpaEntity)
                .where(QUserJpaEntity.userJpaEntity.nickname.eq(nickname))
                .fetchFirst() != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaQueryFactory
                .selectOne()
                .from(QUserJpaEntity.userJpaEntity)
                .where(QUserJpaEntity.userJpaEntity.email.eq(email))
                .fetchFirst() != null;
    }
}
