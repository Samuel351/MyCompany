/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.model;


import java.io.Serializable;
import javax.persistence.Column;
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
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author sscos
 */


@Entity
@Table(name = "Worker")
@Getter
@Setter
@NoArgsConstructor
@Validated
public class WorkerModel extends RepresentationModel<WorkerModel> implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private long id;
    
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;
    
    @NotBlank(message = "Email não pode ser vazio")
    @Email
    private String email;
    
    private String foto;
    
    @NotBlank(message = "RG não pode ser vazio")
    private String RG;
    
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DepartamentModel Departament;

    public WorkerModel(String nome, String email,String foto, String RG, DepartamentModel Departament) {
        this.nome = nome;
        this.email = email;
        this.foto = foto;
        this.RG = RG;
        this.Departament = Departament;
    }
}
