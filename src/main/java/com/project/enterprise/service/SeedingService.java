/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.model.DepartamentoModel;
import com.project.enterprise.model.FuncionarioModel;
import com.project.enterprise.repository.DepartamentoRepository;
import com.project.enterprise.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author sscos
 */

@Component
public class SeedingService implements CommandLineRunner {
    
    @Autowired
    DepartamentoRepository departamentoRepository;
    
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    public void run(String... args) throws Exception {
        departamentoRepository.save(new DepartamentoModel("Vendas", "VEN"));
        
        funcionarioRepository.save(new FuncionarioModel("Jonas", "foto", "3063534", new DepartamentoModel(1)));
        funcionarioRepository.save(new FuncionarioModel("James", "foto", "30623454", new DepartamentoModel(1)));
    }
    
}
