/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.enterprise.repository;

import com.project.enterprise.model.ImageDBModel;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sscos
 */
public interface ImageDBRepository extends CrudRepository<ImageDBModel, Long> {
    Optional<ImageDBModel> findByName(final String name);
}
