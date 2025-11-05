package com.project.havebin.user.adapter.out.persistence.repository;

import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;

import java.util.*;

public interface UserRepositoryCustom {
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
}
