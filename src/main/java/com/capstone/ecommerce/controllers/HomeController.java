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

    @GetMapping("products/productInventory")
    public String getProducts(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products/productInventory";
    }

//  ADD

    @GetMapping("products/productInventory/add")
    public String showAddProduct() {
        return "products/productInventory";
    }

    @PostMapping("products/productInventory/add")
    public String addProductPost(@RequestParam (name = "name") String name,
                                 @RequestParam (name = "color") String color,
                                 @RequestParam (name = "size") String size,
                                 @RequestParam (name = "type") String type,
                                 @RequestParam (name = "price") float price,
                                 @RequestParam (name = "quan") long quan) {
        Product product = new Product();
        product.setName(name);
        product.setColor(color);
        product.setSize(size);
        product.setType(type);
        product.setPrice(price);
        product.setQuantity(quan);

        productRepo.save(product);
        return "redirect:/products/productInventory";
    }

//  DELETE
    @PostMapping("productInventory/delete")
    public String deleteProductPost(@RequestParam (name = "deleteId") long id) {
        productRepo.deleteById(id);
        return "redirect:/products/productInventory";
    }

    //  SEARCH
    @PostMapping("productsInventory/search")
    public String searchProduct(@RequestParam (name = "keyword") String keyword, Model model) {
        List<Product> products = productRepo.findByNameContaining(keyword);
        model.addAttribute("products", products);
        return "products/productInventory";
    }
}