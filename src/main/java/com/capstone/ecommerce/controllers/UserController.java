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
    private AddressRepository addressRepo;

    private final String userNameRegex="[A-Za-z0-9]+";
    private final Pattern usernamePattern=Pattern.compile(userNameRegex);

    //Stores info in variable so it can be used elsewhere, allows information to be malleable

    public UserController(UserRepository users, PasswordEncoder passwordEncoder, AddressRepository addyRepo) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.addressRepo = addyRepo;
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
            error = "Passwords must be longer than four characters!";
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
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "redirect:/";
        }
        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User profileOwner = this.users.getOne(shopper.getId());
        Address ship_address = this.addressRepo.findByUserAndAddresstype(profileOwner, "Shipping");
        if(ship_address == null){
            ship_address = new Address();
            ship_address.setStreet2("");
        }
        Address bill_address = this.addressRepo.findByUserAndAddresstype(profileOwner, "Billing");
        if(bill_address == null){
            bill_address = new Address();
            bill_address.setStreet2("");
        }
        model.addAttribute("bill_address", bill_address);
        model.addAttribute("ship_address", ship_address);
        return "users/profile";
    }




    @PostMapping("/addressUpdate1")
    public String editBillAddress(Address bill_address) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "redirect:/";
        }
        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User profileOwner = this.users.getOne(shopper.getId());
//      ;
//        if (bill_address.getId() > 0) {
//            this.addressRepo.save(bill_address);
//        } else {
            bill_address.setUser(profileOwner);
            bill_address.setAddresstype("Billing");
            this.addressRepo.save(bill_address);
//        }
        return "redirect:/profile";
    }

    @PostMapping("/addressUpdate2")
    public String editShipAddress(Address ship_address) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "redirect:/";
        }
        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User profileOwner = this.users.getOne(shopper.getId());
//        if (ship_address.getId() > 0) {
//            this.addressRepo.save(ship_address);
//        } else {
            ship_address.setUser(profileOwner);
        ship_address.setAddresstype("Shipping");
            this.addressRepo.save(ship_address);
//        }
        return "redirect:/profile";
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