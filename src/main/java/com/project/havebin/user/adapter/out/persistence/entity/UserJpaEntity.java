package com.project.havebin.user.adapter.out.persistence.entity;

import com.project.havebin.user.domain.enums.RoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "profile_image_path")
    private String profileImagePath;

    @Column(name = "role_type")
    private RoleType roleType;

    @Builder
    public UserJpaEntity(Long id, String email, String nickname, String password, String profileImagePath, RoleType roleType) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.profileImagePath = profileImagePath;
        this.roleType = roleType;
    }

    // List를 DB로 저장하기 위한 pk 세팅
    public void setPK(Long id) { this.id = id; }
}
