/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.exception;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author sscos
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    // id que não existe 
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> ApiHandlerException(NoSuchElementException e) {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }

    // Conflito de nome(departamento ou funcionário), email ou sigla
    @ExceptionHandler(value = {HttpClientErrorException.Conflict.class})
    public ResponseEntity<Object> ApiHandlerException(HttpClientErrorException.Conflict e) {
        return new ResponseEntity<>(e, HttpStatus.CONFLICT);
    }

    // Mensagem de campos de validação
    @Override
     protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus status,
                                                                WebRequest request) {
         
         List<ExceptionModel> exceptionList = new ArrayList();
         List<ObjectError> errors = ex.getAllErrors();
         
         for(ObjectError error : errors){
            ExceptionModel exceptionModel = new ExceptionModel(error.getDefaultMessage());
            exceptionList.add(exceptionModel);
         }
        return new ResponseEntity<>(exceptionList, HttpStatus.NOT_ACCEPTABLE);
    }
     
       @ExceptionHandler(AccessDeniedException.class)
        public final ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ExceptionModel exceptionModel = new ExceptionModel(
                ex.getMessage()
        );
        return new ResponseEntity<>(exceptionModel, HttpStatus.FORBIDDEN);
    }
}
