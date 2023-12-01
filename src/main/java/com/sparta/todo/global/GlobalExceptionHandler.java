package com.sparta.todo.global;

import com.sparta.todo.global.exception.AccessDeniedException;
import com.sparta.todo.global.exception.DuplicatedInfoException;
import com.sparta.todo.global.exception.NotFoundException;
import com.sparta.todo.global.exception.NotInvalidTokenException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // NotInvalidTokenException
    @ExceptionHandler({NotInvalidTokenException.class})
    public ResponseEntity<ExceptionResponse> notInvalidTokenExceptionHandler(NotInvalidTokenException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                exceptionResponse,
                HttpStatus.BAD_REQUEST
        );
    }

    // AccessDeniedException
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ExceptionResponse> accessDeniedExceptionHandler(AccessDeniedException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                exceptionResponse,
                HttpStatus.BAD_REQUEST
        );
    }

    // DuplicatedInfoException
    @ExceptionHandler({DuplicatedInfoException.class})
    public ResponseEntity<ExceptionResponse> duplicatedInfoExceptionHandler(DuplicatedInfoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                exceptionResponse,
                HttpStatus.BAD_REQUEST
        );
    }

    // NotFoundException
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(NotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                exceptionResponse,
                HttpStatus.BAD_REQUEST
        );
    }

}
