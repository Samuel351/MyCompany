/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
public class WorkerModel implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private long id;
    
    @NotBlank(message = "Nome cannot be empty")
    private String name;
    
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email pattern invalid")
    private String email;
    
    @Lob
    private byte[] photo;

    @NotNull(message = "RG cannot be empty")
    @Min(9)
    private int RG;
    
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DepartamentModel Departament;
    
    public WorkerModel(String name, String email, byte[] photo, int RG, DepartamentModel Departament) {
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.RG = RG;
        this.Departament = Departament;
    }
}
