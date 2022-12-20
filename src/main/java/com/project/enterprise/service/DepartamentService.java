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
    
    public DepartamentModel findById(Long id){
        
        DepartamentModel departamentModel = departamentRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Não existe departamento com esse ID"));

        List<WorkerModel> workers = workerRepository.findAllByDepartament(id);
    
        if(!workers.isEmpty())
        {
            List<WorkerDto> workerDtos = new ArrayList();
            for(WorkerModel worker : workers){
                workerDtos.add(new WorkerDto(worker.getName(), worker.getEmail()));
            }
            departamentModel.setWorkers(workerDtos);
        }
        return departamentModel;
    }
    
    public List<DepartamentModel> findAll(){
        return (List<DepartamentModel>) departamentRepository.findAll();
    }
    
    @Transactional
    public DepartamentModel save(DepartamentModel departamentModel){
        
        if(departamentRepository.existsByName(departamentModel.getName()))
        {
            throw new ApiConflictException("Esse departamento já existe");
        }
        
        // Cria sigla do departamento com base nas três primeiras letra do nome.
        String sigla = "";
        
        for(int i=0; i<3; i++) 
        {
            sigla += Character.toString(departamentModel.getName().charAt(i));
	}
        
        departamentModel.setSigla(sigla.toUpperCase());
        return departamentRepository.save(departamentModel);
    }
    
    @Transactional
    public DepartamentModel edit(long id, DepartamentModel editDepartament){
        
        // nowDepartament é o departamento atual
        // editDepartament é o que vai substituir os valores do departamento atual
        
        DepartamentModel nowDepartament = departamentRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Não existe departamento com esse ID"));
        
        //Se o nome inserido for igual a um existente no DB e se for diferente do nome atual, lançar uma exceção
        if(departamentRepository.existsByName(editDepartament.getName()) && !editDepartament.getName().equals(nowDepartament.getName()))
        {
            throw new ApiConflictException("Esse departamento já existe");
        }

        return departamentRepository.save(editDepartament);
    }
    
    @Transactional
    public Message DeleteById(long id){
        
        departamentRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Não existe departamento com esse ID"));
        
        departamentRepository.deleteById(id);
        return new Message("Departamento foi deletado");
    }
}
