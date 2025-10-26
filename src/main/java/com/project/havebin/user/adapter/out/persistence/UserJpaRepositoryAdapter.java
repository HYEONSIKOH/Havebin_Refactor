package com.project.havebin.user.adapter.out.persistence;

import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;
import com.project.havebin.user.adapter.out.persistence.mapper.UserMapper;
import com.project.havebin.user.adapter.out.persistence.repository.UserCustomRepository;
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

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = UserMapper.toJpa(user);

//        List<UserJpaEntity> users= repository.findAll();
//        for (UserJpaEntity u : users) {
//            log.info(u.getId() + " " + u.getExternalId().getValue() + " " + u.getEmail().getValue() + " " + u.getPassword().getValue() + " " + u.getNickname().getValue());
//        }

        return UserMapper.toDomain(repository.save(userJpaEntity));
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
