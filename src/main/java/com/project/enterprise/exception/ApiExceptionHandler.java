/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author sscos
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
   
    // id que não existe 
    @ExceptionHandler(value = {ApiNotFoundException.class})
    public ResponseEntity<Object> ApiHandlerException(ApiNotFoundException e){
        ExceptionModel exceptionModel = new ExceptionModel(
                e.getMessage()
        );
        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }
    
    // Conflito de nome(departamento ou funcionário), email ou sigla
    @ExceptionHandler(value = {ApiConflictException.class})
    public ResponseEntity<Object> ApiHandlerException(ApiConflictException e){
        ExceptionModel exceptionModel = new ExceptionModel(
                e.getMessage()
        );
        return new ResponseEntity<>(exceptionModel, HttpStatus.CONFLICT);
    }
}
