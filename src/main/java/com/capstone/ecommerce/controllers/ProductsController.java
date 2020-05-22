package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.repositories.ProductRepository;
import com.capstone.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductsController {

    private ProductRepository productRepo;
    private UserRepository userDao;

//  CONSTRUCTOR
    public ProductsController(ProductRepository productRepo, UserRepository userDao) {
        this.productRepo = productRepo;
        this.userDao = userDao;
    }

    @GetMapping("/products")
    public String productsIndex() {

    }

    @GetMapping("products/t-shirts")
    public String tshirts() {

    }



    @GetMapping("products/{id}")
    public String individualProduct(Model model, @PathVariable long id) {
        Product aProduct = productRepo.getOne(id);
        model.addAttribute("title","View a Single Product");
        model.addAttribute("product", aProduct);
        return "products/show";
    }

}
