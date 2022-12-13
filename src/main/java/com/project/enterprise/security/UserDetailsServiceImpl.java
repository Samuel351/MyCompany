/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.security;


import com.project.enterprise.model.UserModel;
import com.project.enterprise.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author sscos
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{
    // Autentica usuário
    
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário not found"));
        
        return new User(user.getEmail(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }
    
    
    
}
