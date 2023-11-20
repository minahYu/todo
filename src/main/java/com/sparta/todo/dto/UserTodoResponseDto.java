package com.sparta.todo.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserTodoResponseDto {
    private String username;
    private List<TodoResponseDto> todosList;

    public UserTodoResponseDto(UserInfoDto user, List<TodoResponseDto> todosList) {
        this.username = user.getUsername();
        this.todosList = todosList;
    }
}
