/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.exception.ApiConflictException;
import com.project.enterprise.exception.ApiNotFoundException;
import com.project.enterprise.model.DepartamentModel;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.enterprise.repository.DepartamentRepository;

/**
 *
 * @author sscos
 */

@Service
public class DepartamentService {
    
    @Autowired
    DepartamentRepository departamentRepository;
    
    public Optional <DepartamentModel> findById(Long id){
    Optional <DepartamentModel> optionalDepartament = departamentRepository.findById(id);
    if(!optionalDepartament.isPresent()){
        throw new ApiNotFoundException("Não existe departamento com o ID inserido");
    }
        DepartamentModel departament = optionalDepartament.get();
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
