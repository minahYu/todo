package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.dto.UserTodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<TodoResponseDto> createTodo(@RequestBody TodoRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) { // 생성
        return todoService.createTodo(requestDto, userDetails.getUser());
    }

    @GetMapping("/todos")
    public List<UserTodoResponseDto> getTodos() { // 조회
        return todoService.getTodos();
    }

    @GetMapping("/todos/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id) { // 조회

        return todoService.getTodo(id);
    }

    @PutMapping("/todos/{id}")
    public TodoResponseDto updateTodo(@RequestBody TodoRequestDto requestDto, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) { // 수정
        return todoService.updateTodo(requestDto, id, userDetails.getUser());
    }

    @PatchMapping("/todos/{id}")
    public TodoResponseDto completeTodo(@RequestBody TodoRequestDto requestDto, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) { // 수정
        return todoService.completeTodo(requestDto, id, userDetails.getUser());
    }
}
