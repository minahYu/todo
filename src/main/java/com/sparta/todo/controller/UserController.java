package com.sparta.todo.controller;

import com.sparta.todo.dto.SignupRequestDto;
import com.sparta.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;


@Slf4j
@Controller
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        ResponseEntity responseEntity = null;
        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrorList) {
                log.error(fieldError.getField());
                responseEntity = new ResponseEntity(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            userService.signup(requestDto);
            responseEntity = new ResponseEntity("회원가입을 완료하였습니다.", HttpStatus.OK);
        }
        return responseEntity;
    }

    @PostMapping("/login")
    public void login() {

    }
}
