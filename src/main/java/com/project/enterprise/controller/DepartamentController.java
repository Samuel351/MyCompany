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
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> insertDepartament(@RequestBody @Valid DepartamentModel departamentModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentService.save(departamentModel));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<DepartamentModel>> getDepartaments(){
        return ResponseEntity.status(HttpStatus.OK).body(departamentService.findAll());
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getDepartament(@PathVariable(value = "id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(departamentService.findById(id));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDepartament(@PathVariable(value = "id") long id, @RequestBody @Valid DepartamentModel departamentModel){
        return ResponseEntity.status(HttpStatus.OK).body(departamentService.edit(id, departamentModel));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDepartament(@PathVariable(value = "id") long id){
        departamentService.DeleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Departamento deletado!");
    }
}
