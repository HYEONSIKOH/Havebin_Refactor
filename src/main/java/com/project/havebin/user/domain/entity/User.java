package com.project.havebin.user.domain.entity;

import com.project.havebin.user.domain.enums.RoleType;
import com.project.havebin.user.domain.vo.*;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private final UserNo id;
    private Email email;
    private Nickname nickname;
    private Password password;
    private ProfileImagePath profileImagePath;
    private final RoleType roleType;

    @Builder
    public User(Email email, Nickname nickname, Password password, ProfileImagePath profileImagePath, RoleType roleType) {
        this.id = new UserNo();
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.profileImagePath = profileImagePath;
        this.roleType = roleType;
    }

    public User() {
        this.id = new UserNo();
        this.roleType = RoleType.USER;
        this.profileImagePath = new ProfileImagePath();
    }

    public User(RoleType roleType) {
        this.id = new UserNo();
        this.roleType = roleType;
        this.profileImagePath = new ProfileImagePath();
    }

    public User create(Email email, Nickname nickname, Password password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        return this;
    }

    public void updateNickname(Nickname nickname) {
        this.nickname = nickname;
    }
}
