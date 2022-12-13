/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.controller;

import com.project.enterprise.model.ImageModel;
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
@RequestMapping("/photos")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {
    
    @Autowired
    ImageService imageService;
    
    @Autowired
    WorkerService workerService;
    
    /*@PostMapping
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        
        ImageModel image = imageService.store(file);
        
      return ResponseEntity.status(HttpStatus.OK)
              .body(image);
    }*/
    
    @GetMapping
    public ResponseEntity<Object> getFile(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageService.getAllFiles());
    }
    
    
    @PostMapping
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("file")MultipartFile file) throws IOException {
            String uploadImage = imageService.uploadImageToFileSystem(file);
            return ResponseEntity.status(HttpStatus.OK)
                            .body(uploadImage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable(value = "id") long id) throws IOException {
            byte[] imageData=imageService.downloadImageFromFileSystem(id);
            return ResponseEntity.status(HttpStatus.OK)
                            .contentType(MediaType.valueOf("image/png"))
                            .body(imageData);

    }
  }
    

