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
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
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
          
    // Salvar imagem no banco de dados
    @Transactional
    public ImageModel save(ImageModel image) throws IOException {
        return imageRepository.save(image);
    }
    
    public ImageModel findByName(String nome) {
      return imageRepository.findByName(nome).get();
    }

      public List<ImageModel> getAllFiles() {
        return (List<ImageModel>) imageRepository.findAll();
    }
     
     
    // Salvar imagem nas pastas do computador
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
    String filePath=PATH+file.getOriginalFilename();

        ImageModel image=imageRepository.save(new ImageModel(file.getOriginalFilename(), file.getContentType(), filePath));

        file.transferTo(new File(filePath));

        if (image != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(long id) throws IOException {
        Optional<ImageModel> image = imageRepository.findById(id);
        String filePath=image.get().getPath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
    
    
}
