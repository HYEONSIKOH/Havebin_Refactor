package com.project.havebin.user.adapter.out.persistence;

import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;
import com.project.havebin.user.adapter.out.persistence.mapper.UserMapper;
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
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = UserMapper.toJpa(user);
        userRepository.save(userJpaEntity);

        return UserMapper.toDomain(userRepository.save(userJpaEntity));
    }

    @Override
    public boolean duplicateNickname(Nickname nickname) {
        return userRepository.existsByNickname(nickname.value());
    }

    @Override
    public User getUserDataById(Long id) {
        Optional<UserJpaEntity> userJpaEntityOptional = userRepository.findById(id);

        return userJpaEntityOptional
                .map(UserMapper::toDomain)
                .orElse(null);
    }

    @Override
    public boolean duplicateEmail(Email email) {
        return userRepository.existsByEmail(email.value());
    }
}
