package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.dto.UserInfoDto;
import com.sparta.todo.dto.UserTodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    // 생성
    public List<TodoResponseDto> createTodo(TodoRequestDto requestDto, User user) {
        Todo saveTodo = todoRepository.save(new Todo(requestDto, user));

        List<Todo> todoList = todoRepository.findUserAndTitleByUser(saveTodo.getUser());
        List<TodoResponseDto> responseDtoList = new ArrayList<>();

        for(Todo todo : todoList) {
            responseDtoList.add(new TodoResponseDto(todo));
        }

        return responseDtoList;
    }

    // 조회
    public List<UserTodoResponseDto> getTodos() {
        List<Todo> sortedCards = todoRepository.findAllByOrderByCreatedAtDesc();
        List<TodoResponseDto> cardList = new ArrayList<>();

        List<User> users = userRepository.findAll();
        List<UserInfoDto> userList = new ArrayList<>();

        List<UserTodoResponseDto> userCards = new ArrayList<>();

        for(User user : users) {
            userList.add(new UserInfoDto(user));
        }

        for(Todo card : sortedCards) {
            cardList.add(new TodoResponseDto(card));
        }

        for(UserInfoDto user : userList) {
            userCards.add(new UserTodoResponseDto(user, cardList));
        }

        return userCards;
    }

    public TodoResponseDto getTodo(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()) {
            return new TodoResponseDto(todo.get());
        } else {
            throw new IllegalArgumentException("해당하는 할 일이 존재하지 않습니다.");
        }
    }

    // 수정
    @Transactional
    public TodoResponseDto updateTodo(TodoRequestDto requestDto, Long id, User user) {
        Optional<Todo> findTodo = todoRepository.findById(id);

        if(findTodo.isPresent() && user.getId().equals(findTodo.get().getUser().getId())) {
            findTodo.get().update(requestDto);
        } else {
            throw new IllegalArgumentException("다른 사용자의 할 일은 수정 불가합니다.");
        }

        return getTodo(id);
    }

    // 수정
//    @Transactional
//    public TodoResponseDto completeTodo(TodoRequestDto requestDto, Long id) {
//        Optional<Todo> findTodo = todoRepository.findById(id);
//
//        if(findTodo.isPresent()) {
//            findTodo.get().update(requestDto);
//        } else {
//            throw new IllegalArgumentException("해당하는 할 일이 존재하지 않습니다.");
//        }
//
//        return getTodo(id);
//    }
}
