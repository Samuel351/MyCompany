/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.enterprise.repository;

import com.project.enterprise.model.WorkerModel;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sscos
 */

@Repository
public interface WorkerRepository extends CrudRepository<WorkerModel, Long> {
    
    boolean existsByEmail(final String email);
    
    @Query(
    value = "SELECT * FROM WORKER u WHERE u.departament_id = ?", 
    nativeQuery = true)
    List<WorkerModel> findAllByDepartament(long id);
    
}
