package com.sparta.todo.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String contents;
    private Long toodId;
}
