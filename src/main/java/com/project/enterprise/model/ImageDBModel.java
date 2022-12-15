/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author sscos
 */

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Photos_db")
public class ImageDBModel {
      
    @Id
    private long id;
    
    private String name;


    private String type;

    @Lob
    private byte[] data;
    
    public ImageDBModel(long id){
        this.id = id;
    }
    
    public ImageDBModel(String name, String type, byte[] data){
        this.name = name;
        this.type = type;
        this.data = data;
    }
    
}
