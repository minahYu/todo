package com.sparta.todo.dto;

import com.sparta.todo.CommentTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("회원가입 요청 DTO 유효성 검사")
class SignupRequestDtoTest implements CommentTest {

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
        requestDto = new SignupRequestDto(TEST_USER_NAME, TEST_USER_PASSWORD);

        // when
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

        // then
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("유효성 검사 : 잘못된 아이디 입력으로 실패")
    void test2() {
        // given
        requestDto = new SignupRequestDto(TEST_USER_NAME, TEST_USER_PASSWORD);

        // when
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

        // then
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("유효성 검사 : 잘못된 아이디(글자 제한)와 비밀번호(글자 제한 & 길이 제한) 입력으로 실패")
    void test3() {
        // given
        requestDto = new SignupRequestDto(TEST_USER_NAME, TEST_USER_PASSWORD);

        // when
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(requestDto);

        // then
        assertEquals(3, violations.size());
    }
}