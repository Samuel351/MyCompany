/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.model.UserModel;
import com.project.enterprise.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sscos
 */
@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;
    
    public Optional<UserModel> findById(long id){
        return userRepository.findById(id);
    }
    
    public List<UserModel> findAll(){
        return (List<UserModel>) userRepository.findAll();
    }
    
}
