package com.project.havebin.user.adapter.out.persistence;

import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;
import com.project.havebin.user.adapter.out.persistence.mapper.UserMapper;
import com.project.havebin.user.adapter.out.persistence.repository.UserCustomRepository;
import com.project.havebin.user.adapter.out.persistence.repository.UserRepository;
import com.project.havebin.user.application.port.out.UserRepositoryPort;
import com.project.havebin.user.domain.entity.User;
import com.project.havebin.user.domain.vo.Email;
import com.project.havebin.user.domain.vo.Nickname;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserJpaRepositoryAdapter implements UserRepositoryPort {
    //private final UserRepository repository;
    private final UserCustomRepository repository;
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = UserMapper.toJpa(user);
        userRepository.save(userJpaEntity);

        return UserMapper.toDomain(userRepository.save(userJpaEntity));
    }

    @Override
    public boolean duplicateNickname(Nickname nickname) {
        return repository.existsByUsername(nickname.getValue());
    }

    @Override
    public User getUserDataById(Long id) {
        Optional<UserJpaEntity> userJpaEntityOptional = repository.findById(id);

        return userJpaEntityOptional
                .map(entity -> UserMapper.toDomain(entity))
                .orElse(null);
    }

    @Override
    public boolean duplicateEmail(Email email) {
        if (repository.existsByEmail(email.getValue())) {
            return true;
        }

        return false;
    }
}
