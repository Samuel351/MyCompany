/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.enterprise.model.WorkerModel;
import com.project.enterprise.service.ImageService;
import com.project.enterprise.service.WorkerService;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sscos
 */
@RestController
@RequestMapping("/worker")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkerController {
    
    @Autowired
    WorkerService workerService;
    
    @Autowired
    ImageService imageService;
    
    @Autowired
    private Validator validator;
    
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
        public ResponseEntity<Object> insertWorker(@RequestPart(value = "worker") String worker, @RequestPart(value="file") MultipartFile file) throws IOException{
            ObjectMapper map = new ObjectMapper();
            WorkerModel workerModel = map.readValue(worker, WorkerModel.class);
            validator.validate(workerModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(workerService.saveWorkerAndPhoto(workerModel, file));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<WorkerModel>> getWorkers(){
        return ResponseEntity.status(HttpStatus.OK).body(workerService.findAll());
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getWorker(@PathVariable(value = "id") long id){  
        return ResponseEntity.status(HttpStatus.OK).body(workerService.findById(id));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWorker(@PathVariable(value = "id") long id, @RequestBody @Valid WorkerModel workerModel){
        return ResponseEntity.status(HttpStatus.OK).body(workerService.edit(id, workerModel));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> patchPhoto(@PathVariable(value = "id") long id, @RequestPart("file") MultipartFile file) throws IOException{
        return ResponseEntity.status(HttpStatus.OK).body(workerService.editPhoto(id, file));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWorker(@PathVariable(value = "id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(workerService.DeleteById(id));
    }
    
    
}
