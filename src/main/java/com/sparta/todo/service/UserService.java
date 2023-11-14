package com.sparta.todo.service;

import com.sparta.todo.dto.SignupRequestDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {
    PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        // DB에 중복된 username이 있는지 확인
        Optional<User> usernameCheck = userRepository.findByUsername(username);
        System.out.println(usernameCheck);
        if(usernameCheck.isPresent()) {
            // orElseThrow()?
            throw new IllegalArgumentException("중복된 username이 있습니다.");
        }

        // 회원 저장
        // But 작업 중 오류 발생할 경우 모든 작업 취소(rollback)
        User user = new User(username, password);
        System.out.println("Password : " + requestDto.getPassword());
        userRepository.save(user);
    }
}
