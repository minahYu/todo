package com.sparta.todo.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SignupRequestDtoTest {

    SignupRequestDto requestDto;
    ValidatorFactory factory;
    Validator validator;

    @BeforeEach
    void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("유효성 검사 : 성공")
    void test1() {
        // given
        String username = "user12345";
        String password = "Spring03";

        // when
        requestDto = new SignupRequestDto(username, password);
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

        // then
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("유효성 검사 : 잘못된 아이디 입력으로 실패")
    void test2() {
        // given
        String username = "user";
        String password = "Spring03";

        // when
        requestDto = new SignupRequestDto(username, password);
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

        for(ConstraintViolation<SignupRequestDto> violation : violations) {
            System.out.println(violation);
        }
        // then
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("유효성 검사 : 잘못된 아이디(글자 제한)와 비밀번호(글자 제한 & 길이 제한) 입력으로 실패")
    void test3() {
        // given
        String username = "user";
        String password = "spring";

        // when
        requestDto = new SignupRequestDto(username, password);
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

        for(ConstraintViolation<SignupRequestDto> violation : violations) {
            System.out.println(violation);
        }
        // then
        assertEquals(3, violations.size());
    }
}