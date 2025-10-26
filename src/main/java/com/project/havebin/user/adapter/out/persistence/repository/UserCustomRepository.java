package com.project.havebin.user.adapter.out.persistence.repository;

import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;
import com.project.havebin.user.domain.vo.Nickname;
import org.springframework.stereotype.Repository;

import java.util.*;

public interface UserCustomRepository {
    List<UserJpaEntity> findAll();

    UserJpaEntity save(UserJpaEntity userJpaEntity);
    boolean existsByUsername(String nickname);
    Optional<UserJpaEntity> findById(Long id);

    boolean existsByEmail(String email);
}
