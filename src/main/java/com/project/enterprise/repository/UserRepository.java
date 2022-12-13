/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.enterprise.repository;


import com.project.enterprise.model.UserModel;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sscos
 */
@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
   
    Optional <UserModel> findByEmail (final String nome);
    
    Boolean existsByEmail (final String email);
}
