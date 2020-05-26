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
public class HomeController {

    @GetMapping("/")
    public String welcome() {
        return "home";
    }

}