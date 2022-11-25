/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.model.DepartamentModel;
import com.project.enterprise.model.RoleModel;
import com.project.enterprise.model.UserModel;
import com.project.enterprise.model.WorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.project.enterprise.repository.DepartamentRepository;
import com.project.enterprise.repository.UserRepository;
import com.project.enterprise.repository.WorkerRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sscos
 */

@Component
public class SeedingService implements CommandLineRunner {
    
    @Autowired
    DepartamentRepository departamentoRepository;
    
    @Autowired
    WorkerRepository funcionarioRepository;
    
    @Autowired
    UserRepository userRepository;
   

    @Override
    public void run(String... args) throws Exception {
        departamentoRepository.save(new DepartamentModel("Vendas", "VEN"));
        departamentoRepository.save(new DepartamentModel("Marketing", "MAR"));
        
        funcionarioRepository.save(new WorkerModel("Jonas", "jonas@gmail.com","foto", "3063534", new DepartamentModel(1)));
        funcionarioRepository.save(new WorkerModel("James","james@gmail.com","foto", "30623454", new DepartamentModel(2)));
        funcionarioRepository.save(new WorkerModel("Carlos", "carlo@gmail.com","foto", "3063534", new DepartamentModel(1)));
        funcionarioRepository.save(new WorkerModel("Carla","carla@gmail.com" ,"foto", "30623454", new DepartamentModel(2)));
        funcionarioRepository.save(new WorkerModel("Emily", "emily@gmail.com","foto", "3063534", new DepartamentModel(1)));
        funcionarioRepository.save(new WorkerModel("Steve","steve@gmail.com" ,"foto", "30623454", new DepartamentModel(2)));
        funcionarioRepository.save(new WorkerModel("Carol", "carol@gmail.com","foto", "3063534", new DepartamentModel(1)));
        funcionarioRepository.save(new WorkerModel("Giovanne","giovanne@gmail.com","foto", "30623454", new DepartamentModel(2)));
        
        userRepository.save(new UserModel("james@gmail.com", "123456"));
        userRepository.save(new UserModel("carlos@gmail.com", "123456"));
       
        
    }
    
}
