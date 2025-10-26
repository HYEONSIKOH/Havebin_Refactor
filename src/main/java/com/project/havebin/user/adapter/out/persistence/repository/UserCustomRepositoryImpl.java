package com.project.havebin.user.adapter.out.persistence.repository;

import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;
import com.project.havebin.user.domain.vo.Nickname;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private static final List<UserJpaEntity> userTable = new ArrayList<>();
    private static Long newId = -1L;

    @Override
    public List<UserJpaEntity> findAll() {
        return userTable;
    }

    @Override
    public UserJpaEntity save(UserJpaEntity userJpaEntity) {
        if (userJpaEntity.getId() == null) {
            if (newId.equals(-1L)) {
                for (UserJpaEntity jpaEntity : userTable) {
                    newId = Math.max(newId, jpaEntity.getId());
                }
            }

            userJpaEntity.setPK(newId);
            userTable.add(userJpaEntity);

            return userJpaEntity;
        } else {
            boolean isContained = false;
            for (int i = 0; i < userTable.size(); i++) {
                if (userTable.get(i).getId().equals(userJpaEntity.getId())) {
                    userTable.set(i, userJpaEntity);
                    isContained = true;
                    break;
                }
            }

            if (!isContained) {
                if (userJpaEntity.getId() > newId) { newId = userJpaEntity.getId(); }
                userTable.add(userJpaEntity);
            }

            return userJpaEntity;
        }
    }

    @Override
    public boolean existsByUsername(String nickname) {
        for (UserJpaEntity user : userTable) {
            if (user.getNickname().equals(nickname)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Optional<UserJpaEntity> findById(Long id) {
        for (UserJpaEntity user : userTable) {
            if (user.getId().equals(id))
                return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public boolean existsByEmail(String email) {
        for (UserJpaEntity user : userTable) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }
}
