package com.capstone.ecommerce.controllers;
import com.capstone.ecommerce.model.ShoppingCart;

import com.capstone.ecommerce.model.Address;

import com.capstone.ecommerce.model.User;
import com.capstone.ecommerce.model.UserWithRoles;
import com.capstone.ecommerce.repositories.AddressRepository;
import com.capstone.ecommerce.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//Tells controller where to get info from


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@SessionAttributes("products")
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private AddressRepository addressRepository;

    private final String userNameRegex="[A-Za-z0-9]+";
    private final Pattern usernamePattern=Pattern.compile(userNameRegex);

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
    public String saveUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        String error = "";
        if(user.getUsername()==null){
            error = "No value provided for username!";
            redirectAttributes.addFlashAttribute("error", error);

            return "redirect:register";
        }
        Matcher usernameMatcher=usernamePattern.matcher(user.getUsername());
        if(!usernameMatcher.matches()){
            error = "Bad characters in username. Value may only be comprised of letters and numbers.";
            redirectAttributes.addFlashAttribute("error", error);

            return "redirect:register";
        }
        if(this.users.findByUsername(user.getUsername()) != null){
            error = "That username is already in use!";
            redirectAttributes.addFlashAttribute("error", error);

            return "redirect:register";
        }
        if(this.users.findByEmail(user.getEmail()) != null){
            error = "That email is already in use!";
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:register";
        }
        if(user.getPassword().length() <= 4){
            error = "Passwords must longer than four characters!";
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:register";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setAdmin(false);
        users.save(user);
        User authenticator = this.users.findByUsername(user.getUsername());
        redirectAttributes.addFlashAttribute("user", authenticator);
        authenticate(authenticator);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String displayProfile(Model model){

    }


    private void authenticate(User user) {
        // Notice how we're using an empty list for the roles
        UserDetails userDetails = new UserWithRoles(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);
    }
}