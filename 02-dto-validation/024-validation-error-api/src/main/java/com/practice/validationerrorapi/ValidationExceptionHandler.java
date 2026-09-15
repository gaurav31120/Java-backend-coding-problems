package com.practice.validationerrorapi;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Map<String, String> map = new HashMap<>();

        for(FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            map.put(fieldName, message);


        }

        return map;


    }
}
