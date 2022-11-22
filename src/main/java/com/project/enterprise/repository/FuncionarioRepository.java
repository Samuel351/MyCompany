/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.enterprise.repository;

import com.project.enterprise.model.FuncionarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sscos
 */

@Repository
public interface FuncionarioRepository extends CrudRepository<FuncionarioModel, Long> {
    
}
