package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("TodoService 테스트")
class TodoServiceTest {
    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @Mock
    UserRepository userRepository;

/*    @Test
    @DisplayName("할일 생성")
    void test1() {
        String username = "user";
        String password = "Spring03";
        User user = new User(username, password);
        TodoRequestDto requestDto = TodoRequestDto.builder()
                .title("자바")
                .contents("강의듣기")
                .build();
        Todo testTodo = new Todo(requestDto, user);
        given(todoRepository.save(any(Todo.class))).willReturn(testTodo);

        List<TodoResponseDto> result = todoService.createTodo(requestDto, user);

        assertEquals("자바", result.get(0).getTitle());
        assertEquals("강의듣기", result.get(0).getContents());
    }*/

}