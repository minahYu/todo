package com.sparta.todo.global.exception;

public class DuplicatedInfoException extends RuntimeException {
    public DuplicatedInfoException(String message) {
        super(message);
    }
}
