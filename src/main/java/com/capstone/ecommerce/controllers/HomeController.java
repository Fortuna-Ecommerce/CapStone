package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.ShoppingCart;
import com.capstone.ecommerce.repositories.ProductRepository;
import com.capstone.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("products")
public class HomeController {

    @GetMapping("/")
    public String welcome(Model model) {

        if(model.getAttribute("products") == null) {
            ShoppingCart products = new ShoppingCart();
            model.addAttribute("products", products);
        }

        return "home";
    }
}