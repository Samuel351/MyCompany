/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.persistence.jpa.config.Cascade;

/**
 *
 * @author sscos
 */

@Getter
@Setter
@Entity
@Table(name = "Funcionario")
public class FuncionarioModel implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @NotBlank
    private String nome;
   
    private String foto;
    
    @NotBlank
    private String RG;
    
    @ManyToOne
    @JoinColumn
    private DepartamentoModel IdDepartamento;

    public FuncionarioModel() {
    }
    
    public FuncionarioModel(long id, String nome, String foto, String RG, DepartamentoModel IdDepartamento) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.RG = RG;
        this.IdDepartamento = IdDepartamento;
    }

    public FuncionarioModel(String nome, String foto, String RG, DepartamentoModel IdDepartamento) {
        this.nome = nome;
        this.foto = foto;
        this.RG = RG;
        this.IdDepartamento = IdDepartamento;
    }
}
