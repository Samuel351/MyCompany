/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.enterprise.repository;

import com.project.enterprise.model.DepartamentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sscos
 */

@Repository
public interface DepartamentRepository extends CrudRepository<DepartamentModel, Long>{
    
    boolean existsByNome(final String nome);
    boolean existsBySigla(final String sigla);
}
