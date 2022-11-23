/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.exception.ApiConflictException;
import com.project.enterprise.exception.ApiNotFoundException;
import com.project.enterprise.model.WorkerModel;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.enterprise.repository.WorkerRepository;

/**
 *
 * @author sscos
 */

@Service
public class WorkerService {
    
    @Autowired
    WorkerRepository workerRepository;
    
    public Optional <WorkerModel> findById(Long id){
        if(!workerRepository.findById(id).isPresent()){
            throw new ApiNotFoundException("Não existe trabalhador com ID inserido");
        }
        return workerRepository.findById(id);
    }
    
    public List<WorkerModel> findAll(){
        return (List<WorkerModel>) workerRepository.findAll();
    }
    
    public List<WorkerModel> findAllByDepartament(long id){
        return workerRepository.findAllByDepartament(id);
    }
    
    @Transactional
    public WorkerModel save(WorkerModel workerModel){
        if(workerRepository.existsByEmail(workerModel.getEmail())){
            throw new ApiConflictException("Esse email já está cadastrado");
        }
        return workerRepository.save(workerModel);
    }
    
    @Transactional
    public WorkerModel edit(WorkerModel workerModel){
        Optional <WorkerModel> worker = workerRepository.findById(workerModel.getId());
        if(workerRepository.existsByEmail(workerModel.getEmail()) && !worker.get().getEmail().equals(workerModel.getEmail())){
            throw new ApiConflictException("Esse email já está cadastrado");
        }
        return workerRepository.save(workerModel);
    }
  
    @Transactional
    public void DeleteById(long id){
        if(!workerRepository.findById(id).isPresent()){
            throw new ApiNotFoundException("Não existe departamento com o ID inserido");
        }
        workerRepository.deleteById(id);
    }
}
