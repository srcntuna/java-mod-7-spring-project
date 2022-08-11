package com.example.BookListApp.controller;

import com.example.BookListApp.dto.ErrorDTO;
import com.example.BookListApp.dto.ErrorsDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.BookListApp.exception.ValidationException;
import com.example.BookListApp.exception.NotFoundException;


import java.util.Set;

@ControllerAdvice
public class ErrorAdvice
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError(ex.getMessage());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Object> handleInvalidException(RuntimeException ex, WebRequest request) {
        ErrorsDTO errorsDTO = new ErrorsDTO();
        errorsDTO.setErrors(Set.of("validation errors"));
        ex.printStackTrace();
        return handleExceptionInternal(ex, errorsDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorsDTO errorsDTO = new ErrorsDTO();
        ex.printStackTrace();
        errorsDTO.setErrors(Set.of("validation errors"));
        return handleExceptionInternal(ex, errorsDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}