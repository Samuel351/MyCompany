/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.controller;

import com.project.enterprise.model.UserModel;
import com.project.enterprise.model.WorkerModel;
import com.project.enterprise.service.TokenService;
import com.project.enterprise.service.UserService;
import com.project.enterprise.service.WorkerService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sscos
 */

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Valid
public class UserController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    TokenService tokenService;
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }
     
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") long id){  
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> validateUser(@RequestBody @Valid UserModel userModel){ 
        return ResponseEntity.status(HttpStatus.OK).body(userService.ValidateUser(userModel));
    }
    
    @PostMapping("/token")
    public String generateToken(Authentication authentication){ 
        String token= tokenService.generateToken(authentication);
        return token;
    }
}
