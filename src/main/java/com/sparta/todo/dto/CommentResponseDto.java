package com.sparta.todo.dto;

import com.sparta.todo.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String contents;
    private String username;
    private Long todoId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.username = comment.getUser().getUsername();
        this.todoId = comment.getTodoId();
    }
}
