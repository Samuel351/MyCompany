/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.model.RoleModel;
import com.project.enterprise.repository.RoleRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sscos
 */

@Service
public class RoleService {
    
    @Autowired
    RoleRepository roleRepository;
    
    public Optional<RoleModel> findById(long id){
        return roleRepository.findById(id);
    }
}
