package com.dailype.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String filedName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            resp.put(filedName, message);
        });
        return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
    }
}
