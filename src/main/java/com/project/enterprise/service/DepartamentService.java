/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.Dto.WorkerDto;
import com.project.enterprise.exception.ApiConflictException;
import com.project.enterprise.exception.ApiNotFoundException;
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
        throw new ApiNotFoundException("Não existe departamento com o ID inserido");
    }
        List<WorkerDto> workerDtos = new ArrayList();
        List<WorkerModel> workers = workerRepository.findAllByDepartament(id);
        for(WorkerModel worker : workers){
            workerDtos.add(new WorkerDto(worker.getNome(), worker.getEmail()));
        }
        optionalDepartament.get().setWorkers(workerDtos);
        return optionalDepartament;
    }
    
    public List<DepartamentModel> findAll(){
        return (List<DepartamentModel>) departamentRepository.findAll();
    }
    
    @Transactional
    public DepartamentModel save(DepartamentModel departamentModel){
        if(departamentRepository.existsByNome(departamentModel.getNome()))
        {
            throw new ApiConflictException("Já existe um departamento com este nome");
        }
        if(departamentRepository.existsBySigla(departamentModel.getSigla())){
            throw new ApiConflictException("Já existe um departamento com esta sigla");
        }
        return departamentRepository.save(departamentModel);
    }
    
    @Transactional
    public DepartamentModel edit(long id, DepartamentModel departamentModel){
        Optional <DepartamentModel> optionalDepartament = departamentRepository.findById(id);
        if(!optionalDepartament.isPresent()){
             throw new ApiNotFoundException("Não existe departamento com o ID inserido");
        }
        
        //Se o nome inserido for igual a um existente no DB e se for diferente do nome atual, lançar uma exceção
        if(departamentRepository.existsByNome(departamentModel.getNome()) && !departamentModel.getNome().equals(optionalDepartament.get().getNome()))
        {
            throw new ApiConflictException("Já existe um departamento com este nome");
        }
        if(departamentRepository.existsBySigla(departamentModel.getSigla()) && !departamentModel.getSigla().equals(optionalDepartament.get().getSigla()))
        {
            throw new Error("Já existe um departamento com esta sigla");
        }
        departamentModel.setId(id);
        return departamentRepository.save(departamentModel);
    }
    
    @Transactional
    public void DeleteById(long id){
        if(!departamentRepository.findById(id).isPresent()){
            throw new ApiNotFoundException("Não existe departamento com o ID inserido");
        }
        departamentRepository.deleteById(id);
    }
}
