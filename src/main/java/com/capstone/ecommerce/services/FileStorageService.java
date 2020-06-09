//package com.capstone.ecommerce.services;
//
//
//import com.capstone.ecommerce.exception.FileStorageException;
//import com.capstone.ecommerce.exception.MyFileNotFoundException;
//import com.capstone.ecommerce.model.ProductImages;
//import com.capstone.ecommerce.repositories.ProductImagesRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//
//@Service
//public class FileStorageService {
//
//    @Autowired
//    private ProductImagesRepository prodImgRepo;
//
//    public ProductImages storeFile(MultipartFile file) {
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            ProductImages productImage = new ProductImages(fileName, file.getContentType(), file.getPath());
//
//            return prodImgRepo.save(productImage);
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }
//
//    public ProductImages getFile(String fileId) {
//        return prodImgRepo.findById(fileId)
//                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
//    }
//}