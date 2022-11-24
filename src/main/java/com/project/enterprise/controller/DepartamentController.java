/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.controller;

import com.project.enterprise.model.DepartamentModel;
import com.project.enterprise.service.DepartamentService;
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
@RequestMapping("/departament")
@CrossOrigin(origins = "*")
@Valid
public class DepartamentController {
    // Optei por deixa sem HATEOAS por enquanto
    @Autowired
    DepartamentService departamentService;
    
    @PostMapping
    public ResponseEntity<Object> insertDepartament(@RequestBody @Valid DepartamentModel departamentModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentService.save(departamentModel));
    }
    
    @GetMapping
    public ResponseEntity<List<DepartamentModel>> getDepartaments(){
        List<DepartamentModel> departaments = departamentService.findAll();
        
        for (DepartamentModel departament : departaments) {
            Link selfLink = linkTo(DepartamentController.class).slash(departament.getId()).withSelfRel();
            departament.add(selfLink);
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(departamentService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getDepartament(@PathVariable(value = "id") long id){
        Optional <DepartamentModel> optionalDepartament = departamentService.findById(id);
        
        Link delLink = linkTo(DepartamentController.class).slash(optionalDepartament.get().getId()).withRel("Delete").withType("DELETE");
        Link editLink = linkTo(DepartamentController.class).slash(optionalDepartament.get().getId()).withRel("Edit").withType("PUT");
        optionalDepartament.get().add(delLink);
        optionalDepartament.get().add(editLink);
        
        return ResponseEntity.status(HttpStatus.OK).body(optionalDepartament.get());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDepartament(@PathVariable(value = "id") long id, @RequestBody @Valid DepartamentModel departamentModel){
        return ResponseEntity.status(HttpStatus.OK).body(departamentService.edit(id, departamentModel));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDepartament(@PathVariable(value = "id") long id){
        departamentService.DeleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Departamento deletado!");
    }
}
