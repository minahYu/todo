package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.User;
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
    public List<TodoResponseDto> createTodo(@RequestBody TodoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) { // 생성
        return todoService.createTodo(requestDto, userDetails.getUser());
    }

        @GetMapping("/todos")
    public List<TodoResponseDto> getTodos(HttpServletRequest request) { // 조회
        System.out.println("TodoController.getTodos : 인증 완료");
        User user = (User)request.getAttribute("users");
        System.out.println("user.getUsername() = " + user.getUsername());

        return todoService.getTodos();
    }

    @PutMapping("/todos/{id}")
    public Long updateTodo(@RequestBody TodoRequestDto requestDto, @PathVariable Long id) { // 수정
        return todoService.updateTodo(requestDto, id);
    }
}
