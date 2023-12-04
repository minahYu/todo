package com.sparta.todo.service;

import com.sparta.todo.dto.SignupRequestDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService 테스트")
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("회원가입 여부 : 성공")
    void test1() {
        // given
        String username = "user12345";
        String password = "Spring03";
        SignupRequestDto requestDto = SignupRequestDto.builder()
                .username(username)
                .password(password)
                .build();
        User user = new User(username, password);

        // when
        UserService userService = new UserService(userRepository, passwordEncoder);

        // then
        assertEquals(username, user.getUsername());
    }

}