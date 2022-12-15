/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.Dto.Message;
import com.project.enterprise.exception.ApiConflictException;
import com.project.enterprise.model.ImageModel;
import com.project.enterprise.model.WorkerModel;
import com.project.enterprise.repository.DepartamentRepository;
import java.util.List;
import java.util.Optional;
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
    
    public Optional <WorkerModel> findById(Long id){
        Optional <WorkerModel> optionalWorker = workerRepository.findById(id);
        if(!optionalWorker.isPresent()){
            throw new NoSuchElementException("There is no worker with the entered ID");
        }
        return optionalWorker;
    }
    
    public List<WorkerModel> findAll(){
        return (List<WorkerModel>) workerRepository.findAll();
    }
    
    public List<WorkerModel> findAllByDepartament(long id){
        if(!departamentRepository.findById(id).isPresent()){
            throw new NoSuchElementException("There is no worker with the entered ID");
        }
        return workerRepository.findAllByDepartament(id);
    }
    
    @Transactional
    public WorkerModel save(WorkerModel workerModel){
        if(workerRepository.existsByEmail(workerModel.getEmail())){
            throw new ApiConflictException(workerModel.getEmail() + " is already registred");
        }
        if(workerRepository.existsByRG(workerModel.getRG())){
            throw new ApiConflictException(workerModel.getRG() + " is already registred");
        }
        if(!departamentRepository.findById(workerModel.getDepartament().getId()).isPresent())
        {
            throw new NoSuchElementException("The department you are trying to assign to a worker does not exist");
        }
        
        return workerRepository.save(workerModel);
    }
    
        @Transactional
    public WorkerModel saveWorkerAndPhoto(WorkerModel workerModel, MultipartFile file) throws IOException{
        if(workerRepository.existsByEmail(workerModel.getEmail())){
            throw new ApiConflictException(workerModel.getEmail() + " is already registred");
        }
        if(workerRepository.existsByRG(workerModel.getRG())){
            throw new ApiConflictException(workerModel.getRG() + " is already registred");
        }
        if(!departamentRepository.findById(workerModel.getDepartament().getId()).isPresent())
        {
            throw new NoSuchElementException("The department you are trying to assign to a worker does not exist");
        }
        ImageModel image = imageService.uploadImageToFileSystem(file);
        workerModel.setPhoto(image);
        return workerRepository.save(workerModel);
    }
    
    @Transactional
    public WorkerModel edit(long id, WorkerModel workerModel){
        Optional <WorkerModel> optionalWorker = workerRepository.findById(id);
        
        if(!optionalWorker.isPresent()){
            throw new NoSuchElementException("There is no worker with the entered ID");
        }
        if(workerRepository.existsByEmail(workerModel.getEmail()) && !optionalWorker.get().getEmail().equals(workerModel.getEmail())){
            throw new ApiConflictException(workerModel.getEmail() + " is already registred");
        }
        
        if(workerRepository.existsByRG(workerModel.getRG()) && optionalWorker.get().getRG() != workerModel.getRG()){
            throw new ApiConflictException(workerModel.getEmail() + " is already registred");
        }
        if(!departamentRepository.findById(workerModel.getDepartament().getId()).isPresent()){
            throw new NoSuchElementException("The department you are trying to assign to a worker does not exist");
        }
        
        workerModel.setId(id);
        return workerRepository.save(workerModel);
    }
    
    @Transactional
    public WorkerModel editPhoto(long id, WorkerModel workerModel){
        Optional <WorkerModel> optionalWorker = workerRepository.findById(id);
        
        if(!optionalWorker.isPresent()){
            throw new NoSuchElementException("There is no worker with the entered ID");     
        }
        
        workerModel.setId(id);
        return workerRepository.save(workerModel);
    }
  
    @Transactional
    public Message DeleteById(long id){
        Optional <WorkerModel> worker = workerRepository.findById(id);
        if(!worker.isPresent()){
            throw new NoSuchElementException("There is no worker with the entered ID");
        }
        workerRepository.deleteById(id);
        return new Message("Worker has been deleted");
    }
}
