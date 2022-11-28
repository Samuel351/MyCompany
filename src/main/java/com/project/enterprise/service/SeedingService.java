/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.model.RoleType;
import com.project.enterprise.model.DepartamentModel;
import com.project.enterprise.model.RoleModel;
import com.project.enterprise.model.UserModel;
import com.project.enterprise.model.WorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.project.enterprise.repository.DepartamentRepository;
import com.project.enterprise.repository.RoleRepository;
import com.project.enterprise.repository.UserRepository;
import com.project.enterprise.repository.WorkerRepository;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author sscos
 */

@Component
public class SeedingService implements CommandLineRunner {
    
    @Autowired
    DepartamentService departamentService;
    
    @Autowired
    WorkerService workerService;
    
    @Autowired
    UserRepository userRepository;
   
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
    
        RoleModel role_admin = new RoleModel(RoleType.ROLE_ADMIN);
        RoleModel role_user = new RoleModel(RoleType.ROLE_USER);
        roleRepository.save(role_admin);
        roleRepository.save(role_user);
        
        List<RoleModel> list = new ArrayList();
        list.add(role_admin);
        
        UserModel user = new UserModel("admin@gmail.com", new BCryptPasswordEncoder().encode("admin"));
        user.setRoles(list);
        userRepository.save(user);
        
        departamentService.save(new DepartamentModel("Vendas", "VEN"));
        departamentService.save(new DepartamentModel("Marketing", "MAR"));
        
        workerService.save(new WorkerModel("Jonas", "jonas@gmail.com","2346","foto", "26345436", new DepartamentModel(1)));
        workerService.save(new WorkerModel("James","james@gmail.com","37rwg4","foto", "23424235", new DepartamentModel(2)));
        workerService.save(new WorkerModel("Carlos", "carlo@gmail.com","26erg23r","foto", "32345", new DepartamentModel(1)));
        workerService.save(new WorkerModel("Carla","carla@gmail.com" ,"ewf2423","foto", "2354", new DepartamentModel(2)));
        workerService.save(new WorkerModel("Emily", "emily@gmail.com","1343fsdf","foto", "124", new DepartamentModel(1)));
        workerService.save(new WorkerModel("Steve","steve@gmail.com" ,"123421e13556","foto", "2345", new DepartamentModel(2)));
        workerService.save(new WorkerModel("Carol", "carol@gmail.com","35ef24","foto", "14325", new DepartamentModel(1)));
        workerService.save(new WorkerModel("Giovanne","giovanne@gmail.com","f53625t","foto", "25346435", new DepartamentModel(2)));
   
    }
    
}
