package com.sparta.todo.entity;

import com.sparta.todo.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @JoinColumn(name = "todo_id", nullable = false)
    private Long todoId;

    public Comment(CommentRequestDto requestDto, Long todoId, User user) {
        this.contents = requestDto.getContents();
        this.user = user;
        this.todoId = todoId;
    }

    public void update(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
