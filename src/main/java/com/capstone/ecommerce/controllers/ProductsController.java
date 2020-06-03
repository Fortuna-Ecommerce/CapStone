package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductsController {
    private ProductRepository productRepo;
    private ProductRepository imagesRepo;

//  CONSTRUCTOR
    public ProductsController(ProductRepository productRepo, ProductRepository imagesRepo) {
        this.productRepo = productRepo;
        this.imagesRepo = imagesRepo;
    }

    @GetMapping("/products/all")
    public String productsIndex(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("allProducts", products);
        return "products/products";
    }

    @GetMapping("products/t-shirts")
    public String viewTshirts(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products/t-shirts";
    }

    @GetMapping("products/pullover")
    public String viewPullover(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products/pullover";
    }

    @GetMapping("products/hats")
    public String viewHats(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products/hats";
    }

    @GetMapping("products/t-shirts/{id}")
    public String individualTshirt(Model model, @PathVariable("id") long id) {
        Product tshirt = productRepo.getOne(id);
        model.addAttribute("tshirt", tshirt);
        return "products/t-shirts/show";
    }

    @GetMapping("products/pullover/{id}")
    public String individualPullover(Model model, @PathVariable long id) {
        Product aProduct = productRepo.getOne(id);
        model.addAttribute("pullover", aProduct);
        return "products/pullover/show";
    }

    @GetMapping("products/hats/{id}")
    public String individualHat(Model model, @PathVariable long id) {
        Product aProduct = productRepo.getOne(id);
        model.addAttribute("hat", aProduct);
        return "products/hats/show";
    }

    //  SEARCH
    @PostMapping("/products/search")
    public String searchProduct(@RequestParam (name = "keyword") String keyword, Model model) {
        List<Product> products = productRepo.findByNameContaining(keyword);
        model.addAttribute("products", products);
        return "products/index";
    }


}
