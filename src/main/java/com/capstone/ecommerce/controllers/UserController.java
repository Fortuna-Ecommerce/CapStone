package com.capstone.ecommerce.controllers;
import com.capstone.ecommerce.model.ShoppingCart;

import com.capstone.ecommerce.model.Address;

import com.capstone.ecommerce.model.User;
import com.capstone.ecommerce.repositories.AddressRepository;
import com.capstone.ecommerce.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//Tells controller where to get info from


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;


@Controller
@SessionAttributes("products")
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private AddressRepository addressRepository;


    //Stores info in variable so it can be used elsewhere, allows information to be malleable

    public UserController(UserRepository users, PasswordEncoder passwordEncoder, AddressRepository addyRepo) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addyRepo;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        if (model.getAttribute("products") == null) {
            ShoppingCart products = new ShoppingCart();
            model.addAttribute("products", products);
        }
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setAdmin(false);
        users.save(user);
        return "redirect:/login";
    }
}