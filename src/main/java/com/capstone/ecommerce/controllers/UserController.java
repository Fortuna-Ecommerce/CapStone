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


import java.util.List;


@Controller
@SessionAttributes("products")
public class UserController {
    private UserRepository users;
        private PasswordEncoder passwordEncoder;
    private AddressRepository addressRepository;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder, AddressRepository addyRepo) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addyRepo;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        if(model.getAttribute("products") == null) {
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
//REMOVED USER PROFILE FOR NOW :VJP

//        @GetMapping("/users/profile/{name}")
//        public String findByUsername(@PathVariable String name, Model model) {
//            model.addAttribute("name", name);
//
//
//            return "/users/profile";
//        }

//        @GetMapping("/users/profile")
//        public String basicProfile(Model model) {
//            User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            User user = this.users.getOne(person.getId());
//            model.addAttribute("user", user);
//            List<Address> addresses = addressRepository.findByUserId(user.getId());
//            model.addAttribute("addresses", addresses);
//            return "users/profile";
//        }



}