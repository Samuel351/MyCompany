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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author sscos
 */

@Getter
@Setter
@Entity
@Table(name = "Worker")
@Validated
public class WorkerModel extends RepresentationModel<WorkerModel> implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @NotBlank
    private String nome;
    
    @NotBlank
    @Email
    private String email;
   
    private String foto;
    
    @NotBlank
    private String RG;
    
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DepartamentModel Departament;

    public WorkerModel() {
    }

    public WorkerModel(String nome, String email, String foto, String RG, DepartamentModel Departament) {
        this.nome = nome;
        this.email = email;
        this.foto = foto;
        this.RG = RG;
        this.Departament = Departament;
    }
}
