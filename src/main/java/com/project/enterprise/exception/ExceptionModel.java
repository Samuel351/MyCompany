/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.exception;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sscos
 */

@Getter
@Setter
public class ExceptionModel {
    private final String message;

    public ExceptionModel(String message) {
        this.message = message;
    } 
}
