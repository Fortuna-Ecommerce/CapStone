//package com.capstone.ecommerce.controllers;
//
//import com.capstone.ecommerce.model.Product;
//import com.capstone.ecommerce.model.ProductImages;
//import com.capstone.ecommerce.repositories.ProductImagesRepository;
//import com.capstone.ecommerce.repositories.ProductRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.List;
//
//@Controller
//public class FileUploadController {
//
//    private final ProductImagesRepository prodImgRepo;
//    private final ProductRepository productRepo;
//
//
//    @Value("${file-upload-path}")
//    private String uploadPath;
//
//    public FileUploadController(ProductImagesRepository prodImgRepo, ProductRepository productRepo) {
//        this.prodImgRepo = prodImgRepo;
//        this.productRepo = productRepo;
//    }
//
//    @GetMapping("/fileupload")
//    public String showUploadFileForm() {
//        return "fileupload";
//    }
//
//    @PostMapping("/uploadFile")
//    public String saveFile(
//            @RequestParam(name = "file") MultipartFile uploadedFile,
//            Model model
//    ) {
//        List<Product> products = productRepo.findAll();
//        model.addAttribute("allProducts", products);
//        ProductImages prodImg = new ProductImages();
//        String filename = uploadedFile.getOriginalFilename();
//        String filepath = Paths.get(uploadPath, filename).toString();
//        File destinationFile = new File(filepath);
//        try {
//            uploadedFile.transferTo(destinationFile);
//            prodImg.setName(filename);
//            prodImg.setType(uploadedFile.getContentType());
//            prodImg.setPath(filepath);
//            prodImgRepo.save(prodImg);
//            model.addAttribute("message", "File successfully uploaded!");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            model.addAttribute("message", "Oops! Something went wrong! " + e);
//        }
//        return "products/productInventory";
//    }
//}
//
////@RestController
////public class FileUploadController {
////        @RequestMapping(value = "/upload",
////                method = RequestMethod.POST,
////                consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
////
////        public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException
////        {
////            File convertFile = new File("D:/work/" + file.getOriginalFilename());
////            convertFile.createNewFile();
////
////            try (FileOutputStream fout = new FileOutputStream(convertFile))
////            {
////                fout.write(file.getBytes());
////            }
////            catch (Exception exe)
////            {
////                exe.printStackTrace();
////            }
////            return "File has uploaded successfully";
////        }
////
////    }
