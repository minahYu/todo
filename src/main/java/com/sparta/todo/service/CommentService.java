package com.sparta.todo.service;

import com.sparta.todo.dto.CommentRequestDto;
import com.sparta.todo.dto.CommentResponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentService(CommentRepository commentRepository, TodoRepository todoRepository) {
        this.commentRepository = commentRepository;
        this.todoRepository = todoRepository;
    }

    // 댓글 작성
    public CommentResponseDto createComment(CommentRequestDto requestDto,
                                            Long id, User user) {
        // 선택한 할 일의 DB 저장 유무 확인
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()) {
            Comment comment = new Comment(requestDto, id, user);
            commentRepository.save(comment);

            return new CommentResponseDto(comment);
        } else {
            throw new IllegalArgumentException("해당하는 할일이 없습니다.");
        }
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto requestDto, Long id, User user) {
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isPresent() && user.getUsername().equals(comment.get().getUser().getUsername())) {
            comment.get().update(requestDto);
            return new CommentResponseDto(comment.get());
        } else if (comment.isEmpty()) {
            throw new IllegalArgumentException("해당 댓글이 존재하지 않습니다.");
        } else {
            throw new IllegalArgumentException("작성자만 댓글 수정이 가능합니다.");
        }
    }
}
