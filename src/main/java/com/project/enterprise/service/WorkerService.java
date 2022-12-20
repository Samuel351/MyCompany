/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.Dto.Message;
import com.project.enterprise.exception.ApiConflictException;
import com.project.enterprise.model.WorkerModel;
import com.project.enterprise.repository.DepartamentRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.enterprise.repository.WorkerRepository;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sscos
 */

@Service
public class WorkerService {
    
    @Autowired
    WorkerRepository workerRepository;
    
    @Autowired
    DepartamentRepository departamentRepository;
    
    @Autowired
    ImageService imageService;
    
    public WorkerModel findById(Long id){
        return workerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe trabalhador com esse ID"));
    }
    
    public List<WorkerModel> findAll(){
        return (List<WorkerModel>) workerRepository.findAll();
    }
    
    @Transactional
    public WorkerModel save(WorkerModel workerModel){
        Validation(workerModel);
        return workerRepository.save(workerModel);
    }
    
    @Transactional
    public WorkerModel saveWorkerAndPhoto(WorkerModel workerModel, MultipartFile file) throws IOException{
        Validation(workerModel);
        workerModel.setPhoto(imageService.uploadImage(file));
        return workerRepository.save(workerModel);
    }
    
    @Transactional
    public WorkerModel edit(long id, WorkerModel editWorker){
        
        // nowWorker, entidade com os atributos atuais.
        // editWorker, entidade com os atributos novos que vão substituir os originais.

        WorkerModel nowWorker = workerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe trabalhador com esse ID"));
        
        departamentRepository.findById(editWorker.getDepartament().getId())
            .orElseThrow(() -> new NoSuchElementException("O departamento que você está tentando atribuir o trabalhador não existe"));

        if(workerRepository.existsByEmail(editWorker.getEmail()) && !nowWorker.getEmail().equals(editWorker.getEmail())){
            throw new ApiConflictException("Esse email já está registrado");
        }
        
        if(workerRepository.existsByRG(editWorker.getRG()) && nowWorker.getRG() != editWorker.getRG()){
            throw new ApiConflictException(" Esse RG já está registrado");
        }
       
        editWorker.setPhoto(nowWorker.getPhoto());
        return workerRepository.save(editWorker);
    }
 
    @Transactional
    public WorkerModel editPhoto(long id, MultipartFile file) throws IOException{
        
        WorkerModel workerModel = workerRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Não existe trabalhador com esse ID"));
        
        id = workerModel.getPhoto().getId();
        workerModel.setPhoto(imageService.patchImage(id, file));
        return workerRepository.save(workerModel);
    }
  
    @Transactional
    public Message DeleteById(long id){
        
        WorkerModel workerModel = workerRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Não existe trabalhador com esse ID"));
        
        imageService.deleteImage(workerModel.getPhoto().getId());
        workerRepository.deleteById(id);
        return new Message("Trabalhador deletado");
    }
    
    // Função para lançar exceções
    public void Validation(WorkerModel workerModel){
        if(workerRepository.existsByEmail(workerModel.getEmail())){
            throw new ApiConflictException("Esse email já está registrado");
        }
        if(workerRepository.existsByRG(workerModel.getRG())){
            throw new ApiConflictException(" Esse RG já está registrado");
        }
    }
}
