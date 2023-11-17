package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 생성
    public TodoResponseDto createTodo(TodoRequestDto requestDto, User user) {
        Todo todo = todoRepository.save(new Todo(requestDto, user));

        return new TodoResponseDto(todo);
    }

    // 조회
    public List<TodoResponseDto> getTodos() {
        return todoRepository.findAll().stream().map(TodoResponseDto::new).toList();
    }

    // 수정
    @Transactional
    public Long updateTodo(TodoRequestDto requestDto, Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 할일이 존재하지 않습니다."));
        todo.update(requestDto);

        return id;
    }
}
