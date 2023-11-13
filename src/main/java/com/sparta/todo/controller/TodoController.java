package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService service) {
        this.todoService = service;
    }

    @PostMapping("/todos")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) { // 생성
        return todoService.createTodo(requestDto);
    }

    @GetMapping("/todos")
    public List<TodoResponseDto> getTodos() { // 조회
        return todoService.getTodos();
    }

    @PutMapping("/todos/{id}")
    public Long updateTodo(@RequestBody TodoRequestDto requestDto, @PathVariable Long id) { // 수정
        return todoService.updateTodo(requestDto, id);
    }
}
