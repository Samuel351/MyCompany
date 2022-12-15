/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.service;

import com.project.enterprise.Dto.Message;
import com.project.enterprise.model.ImageDBModel;
import com.project.enterprise.model.ImageModel;
import com.project.enterprise.repository.ImageDBRepository;
import com.project.enterprise.repository.ImageRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
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
    
    @Autowired
    ImageDBRepository imageDBRepository;
          
    // Salvar imagem no banco de dados
    public Message UploadToDatabase(MultipartFile file) throws IOException {
        ImageDBModel imageDB = imageDBRepository.save(new ImageDBModel(file.getOriginalFilename(), file.getContentType(), file.getBytes()));
        return new Message("File upload: " + imageDB.getName());
    }
    
    public ImageDBModel downloadImageFromDatabase(String name){
        Optional<ImageDBModel> image = imageDBRepository.findByName(name);
        if(image.isPresent()){
            return image.get();
        }
        
        return null;
    }
    
    public void deleteImageFromDatabase(long id){
         if(imageDBRepository.findById(id).isPresent()){
             imageDBRepository.deleteById(id);
         }
    }
     
     
    // Salvar imagem nas pastas do computador
    public ImageModel uploadImageToFileSystem(MultipartFile file) throws IOException {
    String filePath=PATH+file.getOriginalFilename();

        ImageModel image=imageRepository.save(new ImageModel(file.getOriginalFilename(), file.getContentType(), filePath));

        file.transferTo(new File(filePath));

        if (image != null) {
            return image;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String name) throws IOException {
        Optional<ImageModel> image = imageRepository.findByName(name);
        String filePath=image.get().getPath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
    
    public void deleteImageFromFileSystem(String name){
        Optional<ImageModel> image = imageRepository.findByName(name);
        ImageModel imageModel = image.get();
        if(image.isPresent()){
            File file = new File(imageModel.getPath());
            file.delete();
            imageRepository.deleteById(imageModel.getId());
        }
    }
     
}
