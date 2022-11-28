/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.exception.ApiConflictException;
import com.project.enterprise.exception.ApiNotFoundException;
import com.project.enterprise.model.RoleModel;
import com.project.enterprise.model.UserModel;
import com.project.enterprise.model.WorkerModel;
import com.project.enterprise.repository.DepartamentRepository;
import com.project.enterprise.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.enterprise.repository.WorkerRepository;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author sscos
 */

@Service
public class WorkerService {
    
    @Autowired
    WorkerRepository workerRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    DepartamentRepository departamentRepository;
    
    public Optional <WorkerModel> findById(Long id){
        Optional <WorkerModel> optionalWorker = workerRepository.findById(id);
        if(!optionalWorker.isPresent()){
            throw new ApiNotFoundException("Não existe trabalhador com o ID: " + id);
        }
        return optionalWorker;
    }
    
    public List<WorkerModel> findAll(){
        return (List<WorkerModel>) workerRepository.findAll();
    }
    
    public List<WorkerModel> findAllByDepartament(long id){
        if(!departamentRepository.findById(id).isPresent()){
            throw new ApiNotFoundException("Departamento informado não existe");
        }
        return workerRepository.findAllByDepartament(id);
    }
    
    @Transactional
    public WorkerModel save(WorkerModel workerModel){
        if(workerRepository.existsByEmail(workerModel.getEmail())){
            throw new ApiConflictException(workerModel.getEmail() + " já está cadastrado");
        }
        if(workerRepository.existsByRG(workerModel.getRG())){
            throw new ApiConflictException(workerModel.getRG() + " já está cadastrado");
        }
        UserModel user = new UserModel(workerModel.getEmail(), new BCryptPasswordEncoder().encode(workerModel.getSenha()));
        List<RoleModel> list = new ArrayList();
        list.add(roleService.findById(2).get());
        user.setRoles(list);
        userRepository.save(user);
        return workerRepository.save(workerModel);
    }
    
    @Transactional
    public WorkerModel edit(long id, WorkerModel workerModel){
        Optional <WorkerModel> optionalWorker = workerRepository.findById(id);
        
        if(!optionalWorker.isPresent()){
            throw new ApiNotFoundException("Não existe trabalhador com o ID: " + id);
        }
        if(workerRepository.existsByEmail(workerModel.getEmail()) && !optionalWorker.get().getEmail().equals(workerModel.getEmail())){
            throw new ApiConflictException(workerModel.getEmail() + " já está cadastrado");
        }
        if(!departamentRepository.findById(workerModel.getDepartament().getId()).isPresent()){
            throw new ApiNotFoundException("Departamento atribuído ao trabalhador não existe");
        }
        
        workerModel.setId(optionalWorker.get().getId());
        return workerRepository.save(workerModel);
    }
  
    @Transactional
    public void DeleteById(long id){
        Optional <WorkerModel> worker = workerRepository.findById(id);
        if(!worker.isPresent()){
            throw new ApiNotFoundException("Não existe trabalhador com o ID: " + id);
        }
        
        workerRepository.deleteById(id);
        userRepository.deleteById(id);
    }
}
