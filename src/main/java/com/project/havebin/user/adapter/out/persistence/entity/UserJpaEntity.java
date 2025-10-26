package com.project.havebin.user.adapter.out.persistence.entity;

import com.project.havebin.user.domain.enums.RoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Entity
@Getter
//@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    private String email;

    @Embedded
    private String nickname;

    @Embedded
    private String password;

    @Embedded
    private String profileImagePath;

    @Embedded
    private RoleType roleType;

    @Builder
    public UserJpaEntity(String email, String nickname, String password, String profileImagePath, RoleType roleType) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.profileImagePath = profileImagePath;
        this.roleType = roleType;
    }

    // List를 DB로 저장하기 위한 pk 세팅
    public void setPK(Long id) { this.id = id; }
}
