/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author sscos
 */

@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleModel implements GrantedAuthority,Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "role_id")
    private long roleId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleType roleType;

    @Override
    public String getAuthority() {
       return this.roleType.toString();
    }
}
