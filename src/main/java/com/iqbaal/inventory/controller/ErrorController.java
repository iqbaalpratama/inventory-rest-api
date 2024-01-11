package com.iqbaal.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.iqbaal.inventory.dto.response.APIResponse;
import com.iqbaal.inventory.exception.ProductNotFoundException;
import com.iqbaal.inventory.exception.RequestIsNotValidException;
import com.iqbaal.inventory.exception.UserNotFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> productNotFoundException(ProductNotFoundException exception){
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Failed", 404, exception.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> userNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Failed", 404, exception.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequestIsNotValidException.class)
    public ResponseEntity<APIResponse<Object>> RequestIsNotValidException(RequestIsNotValidException exception){
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Request validation error", 400, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Object>> constraintViolationException(ConstraintViolationException exception) {
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Constraint violation error", 400, exception.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
