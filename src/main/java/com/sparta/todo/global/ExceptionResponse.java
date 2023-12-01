package com.sparta.todo.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private String errorMessage;
    private int statusCode;
}
