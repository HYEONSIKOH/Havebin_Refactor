package com.project.havebin.user.adapter.out.persistence.mapper;

import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;
import com.project.havebin.user.domain.entity.User;
import com.project.havebin.user.domain.vo.Email;
import com.project.havebin.user.domain.vo.Nickname;
import com.project.havebin.user.domain.vo.Password;
import com.project.havebin.user.domain.vo.ProfileImagePath;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserMapper {
    // 도메인 → JPA (신규 생성용)
    public static UserJpaEntity toJpa(User domain) {
        if (domain == null) { return null; }

        return UserJpaEntity.builder()
                .email(domain.getEmail().value())
                .nickname(domain.getNickname().value())
                .password(domain.getPassword().value())
                .profileImagePath(domain.getProfileImagePath().value())
                .roleType(domain.getRoleType())
                .build();
    }

    // JPA → 도메인 (복원용)
    public static User toDomain(UserJpaEntity e) {
        if (e == null) { return null; }

        return new User(
                new Email(e.getEmail()),
                new Nickname(e.getNickname()),
                new Password(e.getPassword()),
                new ProfileImagePath(e.getProfileImagePath()),
                e.getRoleType()
        );
    }
}
