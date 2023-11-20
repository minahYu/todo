package com.sparta.todo.controller;

import com.sparta.todo.dto.CommentRequestDto;
import com.sparta.todo.dto.CommentResponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 작성
    @PostMapping("/comments/{id}")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto,
                                            @PathVariable Long id,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(requestDto, id, userDetails.getUser());
    }

    // 댓글 수정
    @PutMapping("/comments/{id}")
    public CommentResponseDto updateComment(@RequestBody CommentRequestDto requestDto,
                                            @PathVariable Long id,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return commentService.updateComment(requestDto, id, userDetails.getUser());
    }
}
