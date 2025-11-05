package com.project.havebin.application.service;

import com.project.havebin.user.application.UserService;
import com.project.havebin.user.application.port.in.command.CreateUser;
import com.project.havebin.user.application.port.in.command.DuplicateNickname;
import com.project.havebin.user.application.port.in.command.GetUserData;
import com.project.havebin.user.application.port.in.response.RegisterUserResponse;
import com.project.havebin.user.application.port.out.UserRepositoryPort;
import com.project.havebin.user.domain.entity.User;
import com.project.havebin.user.adapter.in.web.dto.response.GetUserDataResDto;
import com.project.havebin.user.domain.enums.RoleType;
import com.project.havebin.user.domain.vo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("UserService 테스트")
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Test
    @DisplayName("유저생성(회원가입)")
    public void createUser() {
        // given
        CreateUser command = CreateUser.builder()
                .email(new Email("test123@test.com"))
                .password(new Password("test123!"))
                .nickname(new Nickname("test01"))
                .build();

        User savedUser = new User();
        when(userRepositoryPort.save(any(User.class))).thenReturn(savedUser);

        // when
        RegisterUserResponse response = userService.createUser(command);

        // then
        verify(userRepositoryPort, times(1)).save(any(User.class));
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("회원가입 실패")
    void createUser_fail() {
        // given
        CreateUser command = CreateUser.builder()
                .email(new Email("fail@test.com"))
                .password(new Password("fail123!"))
                .nickname(new Nickname("failuser"))
                .build();

        // save가 null을 반환 → 예외 발생
        when(userRepositoryPort.save(any(User.class))).thenReturn(null);

        // when
        assertThatThrownBy(() -> userService.createUser(command))
                .isInstanceOf(RuntimeException.class);

        // then
        verify(userRepositoryPort, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("회원가입 - 잘못된 이메일이면 예외, 저장 호출 안 함")
    void createUser_invalidEmail() {
        // given & when & then
        assertThatThrownBy(() ->
                userService.createUser(
                        CreateUser.builder()
                                .email(new Email("not-an-email"))
                                .password(new Password("validPass123!"))
                                .nickname(new Nickname("validNickname"))
                                .build()
                )
        ).isInstanceOf(IllegalArgumentException.class);

        verify(userRepositoryPort, never()).save(any(User.class));
    }

    @Test
    @DisplayName("회원가입 - 잘못된 닉네임이면 예외, 저장 호출 안 함")
    void createUser_invalidNickname() {
        assertThatThrownBy(() ->
                userService.createUser(
                        CreateUser.builder()
                                .email(new Email("test@example.com"))
                                .password(new Password("validPass123!"))
                                .nickname(new Nickname(" "))
                                .build()
                )
        ).isInstanceOf(IllegalArgumentException.class);

        verify(userRepositoryPort, never()).save(any(User.class));
    }

    @Test
    @DisplayName("회원가입 - 잘못된 비밀번호면 예외, 저장 호출 안 함")
    void createUser_invalidPassword() {
        assertThatThrownBy(() ->
                userService.createUser(
                        CreateUser.builder()
                                .email(new Email("test@example.com"))
                                .password(new Password("va"))
                                .nickname(new Nickname("validNickname"))
                                .build()
                )
        ).isInstanceOf(IllegalArgumentException.class);

        verify(userRepositoryPort, never()).save(any(User.class));
    }

    @Test
    @DisplayName("닉네임 중복 체크 - 중복되지 않음")
    public void duplicateNickname() {
        assertThatThrownBy(() -> {
            DuplicateNickname command = DuplicateNickname.builder()
                    .nickname(new Nickname("uniqueNickname"))
                    .build();
            when(userRepositoryPort.duplicateNickname(command.nickname())).thenReturn(false);
            userService.duplicateNickname(command);
        }).isInstanceOf(IllegalArgumentException.class);
        verify(userRepositoryPort, never()).duplicateNickname(any());
    }

    @Test
    @DisplayName("닉네임 중복 체크 - 중복됨, 예외 발생")
    public void duplicateNickname_fail() {
        assertThatThrownBy(() -> {
            DuplicateNickname command = DuplicateNickname.builder()
                    .nickname(new Nickname("duplicateNickname"))
                    .build();
            when(userRepositoryPort.duplicateNickname(command.nickname())).thenReturn(true);
            userService.duplicateNickname(command);
        }).isInstanceOf(IllegalArgumentException.class);
        verify(userRepositoryPort, never()).duplicateNickname(any());
    }

    @Test
    @DisplayName("회원정보 조회 - 성공")
    public void getUserInfo_success() {
        // given
        long userId = 1L;
        GetUserData command = new GetUserData(userId);

        User user = mock(User.class);
        when(userRepositoryPort.getUserDataById(userId)).thenReturn(user);
        when(user.getEmail()).thenReturn(new Email("test@example.com"));
        when(user.getNickname()).thenReturn(new Nickname("tester"));
        when(user.getProfileImagePath()).thenReturn(new ProfileImagePath("profile.jpg"));
        when(user.getRoleType()).thenReturn(RoleType.USER);

        // when
        GetUserDataResDto dto = userService.getUserData(command);

        // then
        verify(userRepositoryPort, times(1)).getUserDataById(userId);
        assertThat(dto.email()).isEqualTo("test@example.com");
        assertThat(dto.nickname()).isEqualTo("tester");
        assertThat(dto.profileImgPath()).isEqualTo("profile.jpg");
        assertThat(dto.roleType()).isEqualTo("USER");
    }

    @Test
    @DisplayName("회원정보 조회 - 존재하지 않음")
    public void getUserInfo_notFound() {
        // given
        long userId = 999L;
        GetUserData command = new GetUserData(userId);
        when(userRepositoryPort.getUserDataById(userId)).thenReturn(null);

        // when & then
        assertThatThrownBy(() -> userService.getUserData(command))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("No User Data");

        verify(userRepositoryPort, times(1)).getUserDataById(userId);
    }
}
