/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    
    @NotBlank
    private String nome;
    
    @NotBlank
    @Size(min = 2)
    private String sigla;

    public DepartamentModel() {
    }
   
     public DepartamentModel(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public DepartamentModel(long id) {
        this.id = id;
    }
}
