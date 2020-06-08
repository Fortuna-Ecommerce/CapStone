package com.capstone.ecommerce.controllers;


import com.capstone.ecommerce.model.ShoppingCart;
import com.capstone.ecommerce.model.User;
import com.capstone.ecommerce.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//Tells controller where to get info from

@Controller
@SessionAttributes("products")
public class UserController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;




    //Stores info in variable so it can be used elsewhere, allows information to be malleable
    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
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
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setAdmin(false);
        userRepo.save(user);
        return "redirect:/login";
    }

//        @GetMapping("/users/profile/{name}")
//        public String findByUsername(@PathVariable String name, Model model) {
//            model.addAttribute("name", name);
//
//
//            return "/users/profile";
//        }

        @GetMapping("/users/profile")
        public String basicProfile(Model model) {
            User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userRepo.getOne(person.getId());
            model.addAttribute("user", user);


            return "/users/profile";
        }

    }