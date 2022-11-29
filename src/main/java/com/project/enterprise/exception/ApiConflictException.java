/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.exception;

/**
 *
 * @author sscos
 */
public class ApiConflictException extends RuntimeException {
    
    private final String message;
    
     public ApiConflictException(String message){
        super(message);
        this.message = message;
    }
    
}
