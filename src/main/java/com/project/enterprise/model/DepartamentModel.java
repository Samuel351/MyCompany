/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.project.enterprise.Dto.WorkerDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sscos
 */
@Getter
@Setter
@Entity
@Table(name = "Departament")
public class DepartamentModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "Name não pode ser vazio")
    private String name;
    
    private String sigla;
    
    @Transient
    @JsonInclude(Include.NON_EMPTY)
    private List<WorkerDto> workers;

    public DepartamentModel() {
    }
   
     public DepartamentModel(String name) {
        this.name = name;
    }

    public DepartamentModel(long id) {
        this.id = id;
    }
}
