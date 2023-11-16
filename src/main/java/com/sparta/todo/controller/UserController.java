package com.sparta.todo.controller;

import com.sparta.todo.dto.SignupRequestDto;
import com.sparta.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup") // 회원가입
    public ResponseEntity signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        System.out.println("Hello " + requestDto.getUsername());
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

/*    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();

        return new UserInfoDto(username);
    }*/
}
