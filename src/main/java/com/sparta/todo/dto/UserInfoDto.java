package com.sparta.todo.dto;

import com.sparta.todo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    private String username;

    public UserInfoDto(User user) {
        this.username = user.getUsername();
    }
}
