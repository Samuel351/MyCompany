/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.repository;

import com.project.enterprise.model.ImageModel;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sscos
 */
@Repository
public interface ImageRepository extends CrudRepository<ImageModel, Long>{
    
    Optional<ImageModel> findByName(final String name);
    
}
