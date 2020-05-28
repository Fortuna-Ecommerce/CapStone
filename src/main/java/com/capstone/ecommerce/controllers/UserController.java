package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.User;
import com.capstone.ecommerce.repositories.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.capstone.ecommerce.repositories.ProductRepository;

import java.util.Optional;

public class UserController {
    private UserRepository userRepo;

    //Constructor
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/profile/id")
    @ResponseBody
    public String userProfile(Model model) {
        Optional<User> users = userRepo.findById();
        model.addAttribute("allUsers", users);
        return "/user/profile";
    }

    @GetMapping("/user/{id}/profile/create")
    @ResponseBody
    public String userProfileCreate(Model model){
        model.addAttribute("allUsers", userRepo.findAll());
        return "/user/{id}/profile/create";
    }

    @GetMapping("/user/{id}/profile/edit")
    @ResponseBody
    public String userProfileEdit(Model model){
        model.addAttribute("allUsers", userRepo.findAll());
        return "/user/{id}/profile/edit";
    }

    @GetMapping("/user/profile/{id}")
        @ResponseBody
    public String viewUserProfile(Model model){
        model.addAttribute("allUsers", userRepo.findAll());
        return "/user/profile/{id}";
        }

    @GetMapping("/user/products/tshirts/{id}/review")
    @ResponseBody
    public String viewReviewsSubmitted(Model model){
        model.addAttribute("allUsers", userRepo.findAll());
        return "/user/products/tshirts/{id}/review";
    }

    @GetMapping("/user/products/tshirts")
    @ResponseBody
    public String viewTshirts(Model model){
        model.addAttribute("allProducts", productRepo.findAll());
        return "/user/products/tshirts";
    }
    @GetMapping("/user/products/tshirt/{id}/checkout/shipping/billing/confirmation")
    @ResponseBody
    public String buyProduct(Model model){
        model.addAttribute("allProducts", productRepo.findAll());
        return "/user/products/tshirt/{id}/checkout/shipping/billing/confirmation";
    }

}
