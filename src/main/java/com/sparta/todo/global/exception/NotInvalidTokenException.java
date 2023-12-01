package com.sparta.todo.global.exception;

import org.aspectj.weaver.ast.Not;

public class NotInvalidTokenException extends RuntimeException {
    public NotInvalidTokenException(String message) {
        super(message);
    }
}
