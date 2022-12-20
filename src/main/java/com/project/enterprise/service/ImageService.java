/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.model.ImageModel;
import com.project.enterprise.repository.ImageRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sscos
 */
@Service
public class ImageService {
    
    String PATH = "C:/front/frontend/fotos/";
    
    @Autowired
    ImageRepository imageRepository;
    
  
    // Salvar imagem nas pastas do computador
    public ImageModel uploadImage(MultipartFile file) throws IOException {
    String filePath=PATH+file.getOriginalFilename();
    
        ImageModel imageModel=imageRepository.save(new ImageModel(file.getOriginalFilename(), file.getContentType(), filePath));

        file.transferTo(new File(filePath));

        if (imageModel != null) {
            return imageModel;
        }
        return null;
    }
    
    // Troca por foto nova e deleta a foto existente
    public ImageModel patchImage(long id, MultipartFile file) throws IOException {
        String filePath=PATH+file.getOriginalFilename();
  
         ImageModel imageModel = imageRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Foto não encontrada"));

        File file_path = new File(imageModel.getPath());
        file_path.delete();
        
        imageModel.setId(id);
        imageModel.setName(file.getOriginalFilename());
        imageModel.setType(file.getContentType());
        imageModel.setPath(filePath);
        
        imageRepository.save(imageModel);

        file.transferTo(new File(filePath));

        return imageModel;
        
    }
    
    public byte[] downloadImage(String name) throws IOException {

        ImageModel imageModel = imageRepository.findByName(name)
            .orElseThrow(() -> new NoSuchElementException("Foto não encontrada"));
        
        byte[] image = Files.readAllBytes(new File(imageModel.getPath()).toPath());
        
        return image;
    }
    
    public void deleteImage(long id){
        
        ImageModel imageModel = imageRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Foto não encontrada"));
        
        File file = new File(imageModel.getPath());
        file.delete();
        imageRepository.deleteById(imageModel.getId());

    }
}
           
   