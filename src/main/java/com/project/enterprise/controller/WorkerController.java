/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.controller;

import com.project.enterprise.model.WorkerModel;
import com.project.enterprise.service.WorkerService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sscos
 */
@RestController
@RequestMapping("/worker")
@CrossOrigin(origins = "*")
public class WorkerController {
    
    @Autowired
    WorkerService workerService;
    
    @PostMapping
        public ResponseEntity<Object> insertWorker(@RequestBody @Valid WorkerModel workerModel){
            return ResponseEntity.status(HttpStatus.CREATED).body(workerService.save(workerModel));
    }
    
    @GetMapping
    public ResponseEntity<List<WorkerModel>> getWorkers(){
        List<WorkerModel> workers = workerService.findAll();
        
        for(WorkerModel worker : workers){
         Link selfLink = linkTo(WorkerController.class).slash(worker.getId()).withSelfRel();
         Link departamentLink = linkTo(DepartamentController.class).slash(worker.getDepartament().getId()).withSelfRel();
         
         worker.add(selfLink);
         worker.getDepartament().add(departamentLink);
        }
        return ResponseEntity.status(HttpStatus.OK).body(workers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getWorker(@PathVariable(value = "id") long id){
        Optional <WorkerModel> optionalWorker = workerService.findById(id);
        
        Link delLink = linkTo(DepartamentController.class).slash(optionalWorker.get().getId()).withRel("Delete").withType("DELETE");
        Link editLink = linkTo(DepartamentController.class).slash(optionalWorker.get().getId()).withRel("Edit").withType("PUT");
        optionalWorker.get().add(delLink);
        optionalWorker.get().add(editLink);
        
        
        return ResponseEntity.status(HttpStatus.OK).body(optionalWorker.get());
    }
    
    
    @GetMapping("/departament/{id}")
    public ResponseEntity<List<WorkerModel>> getWorkersByDepartament(@PathVariable(value = "id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(workerService.findAllByDepartament(id));
    } 
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWorker(@PathVariable(value = "id") long id, @RequestBody @Valid WorkerModel workerModel){
        return ResponseEntity.status(HttpStatus.OK).body(workerService.edit(id, workerModel));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWorker(@PathVariable(value = "id") long id){
        workerService.DeleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
