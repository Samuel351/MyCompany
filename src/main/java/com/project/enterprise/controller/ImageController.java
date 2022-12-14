/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.controller;

import com.project.enterprise.service.ImageService;
import com.project.enterprise.service.WorkerService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sscos
 */

@RestController
@RequestMapping("/photo")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {
    
    @Autowired
    ImageService imageService;
    
    
    // Salvar imagens no sistema de arquivos
    @PostMapping
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("file")MultipartFile file) throws IOException {
            return ResponseEntity.status(HttpStatus.OK)
                            .body(imageService.uploadImage(file));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable(value = "name") String name) throws IOException {
            byte[] imageData=imageService.downloadImage(name);
            return ResponseEntity.status(HttpStatus.OK)
                            .contentType(MediaType.valueOf("image/png"))
                            .body(imageData);

    }

  }
    

