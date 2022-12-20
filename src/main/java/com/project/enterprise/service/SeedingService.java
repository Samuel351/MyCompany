/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.model.RoleType;
import com.project.enterprise.model.DepartamentModel;
import com.project.enterprise.model.ImageModel;
import com.project.enterprise.model.RoleModel;
import com.project.enterprise.model.UserModel;
import com.project.enterprise.model.WorkerModel;
import com.project.enterprise.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.project.enterprise.repository.RoleRepository;
import com.project.enterprise.repository.UserRepository;
import java.util.ArrayList;
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
    
    @Autowired
    ImageRepository imageRepository;
    
    // Populando base de dados
    @Override
    public void run(String... args) throws Exception {
    
        RoleModel role_admin = new RoleModel(RoleType.ROLE_ADMIN);
        RoleModel role_user = new RoleModel(RoleType.ROLE_USER);
        roleRepository.save(role_admin);
        roleRepository.save(role_user);
        
        List<RoleModel> list = new ArrayList();
        list.add(role_admin);
        
        List<RoleModel> list2 = new ArrayList();
        list2.add(role_user);
        
        UserModel admin = new UserModel("admin@gmail.com", new BCryptPasswordEncoder().encode("admin"), list);
        UserModel ceo = new UserModel("ceo@gmail.com", new BCryptPasswordEncoder().encode("ceo"), list);
        UserModel user = new UserModel("user@gmail.com", new BCryptPasswordEncoder().encode("user"), list2);
        userRepository.save(admin);
        userRepository.save(ceo);
        userRepository.save(user);
        
        departamentService.save(new DepartamentModel("Vendas"));
        departamentService.save(new DepartamentModel("Marketing"));
        departamentService.save(new DepartamentModel("Desenvolvimento"));
        
        imageRepository.save(new ImageModel("man_1.jpg", "image/jpg", "C:/front/frontend/fotos/man_1.jpg"));
        imageRepository.save(new ImageModel("man_2.jpg", "image/jpg", "C:/front/frontend/fotos/man_2.jpg"));
        imageRepository.save(new ImageModel("man_3.jpg", "image/jpg", "C:/front/frontend/fotos/man_3.jpg"));
        imageRepository.save(new ImageModel("woman_1.jpg", "image/jpg", "C:/front/frontend/fotos/woman_1.jpg"));
        imageRepository.save(new ImageModel("woman_2.jpg", "image/jpg", "C:/front/frontend/fotos/woman_2.jpg"));
        imageRepository.save(new ImageModel("man_4.jpg", "image/jpg", "C:/front/frontend/fotos/man_4.jpg"));
        imageRepository.save(new ImageModel("woman_3.jpg", "image/jpg", "C:/front/frontend/fotos/woman_3.jpg"));
        imageRepository.save(new ImageModel("man_5.jpg", "image/jpg", "C:/front/frontend/fotos/man_5.jpg"));
               
        workerService.save(new WorkerModel("Jonas", "jonas@gmail.com",new ImageModel(1), 263454369, new DepartamentModel(1)));
        workerService.save(new WorkerModel("James","james@gmail.com",new ImageModel(2), 451254769, new DepartamentModel(2)));
        workerService.save(new WorkerModel("Carlos", "carlo@gmail.com",new ImageModel(3), 194344961, new DepartamentModel(1)));
        workerService.save(new WorkerModel("Carla","carla@gmail.com" ,new ImageModel(4), 452344966, new DepartamentModel(2)));
        workerService.save(new WorkerModel("Emily", "emily@gmail.com",new ImageModel(5), 122344564, new DepartamentModel(1)));
        workerService.save(new WorkerModel("Steve","steve@gmail.com" ,new ImageModel(6), 852399567, new DepartamentModel(2)));
        workerService.save(new WorkerModel("Carol", "carol@gmail.com",new ImageModel(7), 454323159, new DepartamentModel(1)));
        workerService.save(new WorkerModel("Giovanne","giovanne@gmail.com",new ImageModel(8), 352352367, new DepartamentModel(2)));

    }
    
}
