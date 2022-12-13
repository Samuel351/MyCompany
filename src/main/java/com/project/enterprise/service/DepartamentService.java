/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.Dto.Message;
import com.project.enterprise.Dto.WorkerDto;
import com.project.enterprise.exception.ApiConflictException;
import com.project.enterprise.model.DepartamentModel;
import com.project.enterprise.model.WorkerModel;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.enterprise.repository.DepartamentRepository;
import com.project.enterprise.repository.WorkerRepository;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author sscos
 */

@Service
public class DepartamentService {
    
    @Autowired
    DepartamentRepository departamentRepository;
    
    @Autowired
    WorkerRepository workerRepository;
    
    public Optional <DepartamentModel> findById(Long id){
    Optional <DepartamentModel> optionalDepartament = departamentRepository.findById(id);
    if(!optionalDepartament.isPresent()){
        throw new NoSuchElementException("There is no department with the entered ID");
    }
        List<WorkerDto> workerDtos = new ArrayList();
        List<WorkerModel> workers = workerRepository.findAllByDepartament(id);
        for(WorkerModel worker : workers){
            workerDtos.add(new WorkerDto(worker.getName(), worker.getEmail()));
        }
        optionalDepartament.get().setWorkers(workerDtos);
        return optionalDepartament;
    }
    
    public List<DepartamentModel> findAll(){
        return (List<DepartamentModel>) departamentRepository.findAll();
    }
    
    @Transactional
    public DepartamentModel save(DepartamentModel departamentModel){
        if(departamentRepository.existsByName(departamentModel.getName()))
        {
            throw new ApiConflictException("A department with this name already exists");
        }
        String sigla = "";
        for(int i=0; i<3; i++) {
	sigla += Character.toString(departamentModel.getName().charAt(i));
	}
        departamentModel.setSigla(sigla.toUpperCase());
        return departamentRepository.save(departamentModel);
    }
    
    @Transactional
    public DepartamentModel edit(long id, DepartamentModel departamentModel){
        Optional <DepartamentModel> optionalDepartament = departamentRepository.findById(id);
        if(!optionalDepartament.isPresent()){
             throw new NoSuchElementException("There is no department with the entered ID");
        }
        
        //Se o nome inserido for igual a um existente no DB e se for diferente do nome atual, lançar uma exceção
        if(departamentRepository.existsByName(departamentModel.getName()) && !departamentModel.getName().equals(optionalDepartament.get().getName()))
        {
            throw new ApiConflictException("A department with this name already exists");
        }
        departamentModel.setId(id);
        return departamentRepository.save(departamentModel);
    }
    
    @Transactional
    public Message DeleteById(long id){
        if(!departamentRepository.findById(id).isPresent()){
            throw new NoSuchElementException("There is no department with the entered ID");
        }
        departamentRepository.deleteById(id);
        return new Message("Departament has been deleted");
    }
}
