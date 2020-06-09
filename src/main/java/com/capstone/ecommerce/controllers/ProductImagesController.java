//package com.capstone.ecommerce.controllers;
//
//
//import com.capstone.ecommerce.model.ProductImages;
//import com.capstone.ecommerce.model.UploadFileResponse;
//import com.capstone.ecommerce.repositories.ProductImagesRepository;
//import com.capstone.ecommerce.services.FileStorageService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//public class ProductImagesController {
//
//    private static final Logger logger = LoggerFactory.getLogger(ProductImagesController.class);
//
//    @Autowired
//    private FileStorageService fileStorageService;
//
//    @PostMapping("/uploadFile")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//        ProductImages prodImg = fileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/resources/images/")
//                .path(prodImg.getId())
//                .toUriString();
//
//        return new UploadFileResponse(prodImg.getName(), fileDownloadUri,
//                prodImg.getType(), prodImg.getPic());
//    }
//
//}