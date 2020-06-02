package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.ProductImages;
import com.capstone.ecommerce.repositories.ProductImagesRepository;
import com.capstone.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class HomeController {
    private ProductRepository productRepo;
    private ProductImagesRepository productImagesRepo;


    public HomeController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/")
    public String welcome() {
        return "home";
    }

    @GetMapping("admin/productList")
    public String getProducts(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products/productInventory";
    }

//    @PostMapping("admin/productInventory/addProduct")
//    public String addProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {
//        productRepo.save(product);
//        ProductImages productImage = product.getProductImage();
//        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
//        path = Paths.get(rootDirectory + "\\resources\\static\\img\\" + product.getId()+ ".jpg");
//
////        if (productImage !=null && !productImage.isEmpty()) {
////            try {
////            } catch (Exception e) {
////                e.printStackTrace();
////                throw new RuntimeException("Product image failed", e);
////            }
////        }
//        return "redirect:admin/productList";
//    }

    @PostMapping("admin/productInventory/deleteProduct/{id}")
    public String deleteProductPost(@PathVariable String id, Product product) {
        productRepo.delete(product);
        return "redirect:products/productInventory";
    }

    //  SEARCH
    @PostMapping("admin/productsInventory/search")
    public String searchProduct(@RequestParam (name = "keyword") String keyword, Model model) {
        List<Product> products = productRepo.findByNameContaining(keyword);
        model.addAttribute("products", products);
        return "products/productInventory";
    }
}