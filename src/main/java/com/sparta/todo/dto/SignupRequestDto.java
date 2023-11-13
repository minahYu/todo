package com.sparta.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @Size(min = 4, max = 10, message = "4-10글자로 username을 설정해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])[a-z0-9]+$", message = "소문자와 숫자를 포함한 username을 입력해주세요.")
    @NotBlank
    @NotNull(message = "username을 입력해주세요")
    private String username;

    @Size(min = 8, max = 15, message = "8-15글자로 password를 설정해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-z0-9]+$", message = "대소문자와 숫자를 포함한 password를 입력해주세요.")
    @NotBlank
    @NotNull(message = "password를 입력해주세요")
    private String password;

}
