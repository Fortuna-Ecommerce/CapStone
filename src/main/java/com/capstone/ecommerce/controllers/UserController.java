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

//Tells controller where to get info from

@Controller
@SessionAttributes("products")
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private AddressRepository addressRepository;

    //Stores info in variable so it can be used elsewhere, allows information to be malleable





    //Stores info in variable so it can be used elsewhere, allows information to be malleable

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


//        @GetMapping("/users/profile/{name}")
//        public String findByUsername(@PathVariable String name, Model model) {
//            model.addAttribute("name", name);
//
//
//            return "/users/profile";
//        }

    @GetMapping("/users/profile")
    public String basicProfile(Model model) {
        if (model.getAttribute("products") == null) {
            ShoppingCart blankProducts = new ShoppingCart();
            model.addAttribute("products", blankProducts);
        }
        Address bill_address;
        Address ship_address;
        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.users.getOne(person.getId());
        model.addAttribute("user", user);
        if(addressRepository.findByUserAndAddresstype(user, "Billing") == null){
            bill_address = new Address();
        } else {
            bill_address = addressRepository.findByUserAndAddresstype(user, "Billing");
        }
        if(addressRepository.findByUserAndAddresstype(user, "Shipping") == null) {
            ship_address = new Address();
        } else {
            ship_address = addressRepository.findByUserAndAddresstype(user, "Shipping");
        }

        model.addAttribute("bill_address", bill_address);
        model.addAttribute("ship_address", ship_address);
        return "users/profile";
    }



//        @PostMapping("/register")
//        public String saveUser(@ModelAttribute User user) {
//            String hash = passwordEncoder.encode(user.getPassword());
//            user.setPassword(hash);
//            user.setAdmin(false);
//            users.save(user);
//            return "redirect:/login";
//        }
//
//        @GetMapping("/users/profile")
//        public String viewProfile(Model model) {
//            // Get Current user and store to model to be sent to HTML
//            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            model.addAttribute("user", user);
//            return "users/profile";
//        }
//
//    }
//    }

//    @GetMapping("/users/profile")
//    public String viewProfile(Model model) {
//        // Get Current user and store to model to be sent to HTML
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user", user);
//        List<Address> addresses = addressRepository.findAll();
//        model.addAttribute("addresses", addresses);
//        return "users/profile";
//    }


    //Adding more than one address will just cause a lot more work in the long run - can add/edit on same form

//    @GetMapping("users/addresses/add")
//    public String showAddAddress() {
//        return "users/addresses";
//    }
//
//    @PostMapping("users/addresses/add")
//    public String addAddressPost(@RequestParam(name = "first_name") String firstName,
//                                 @RequestParam(name = "last_name") String lastName,
//                                 @RequestParam(name = "address_type") String type,
//                                 @RequestParam(name = "street_1") String street1,
//                                 @RequestParam(name = "street_2") String street2,
//                                 @RequestParam(name = "city") String city,
//                                 @RequestParam(name = "zipcode") int zipcode,
//                                 @RequestParam(name = "state") String state) {
//        Address address = new Address();
//        address.setAddresstype(type);
//        address.setFirstname(firstName);
//        address.setLastname(lastName);
//        address.setStreet1(street1);
//        address.setStreet2(street2);
//        address.setCity(city);
//        address.setState(state);
//        address.setZipcode(zipcode);
//        addressRepository.save(address);
//        return "users/profile";
//    }
//
//    @GetMapping("/users/addresses/edit/{id}")
//    public String getDeleteProfileForm(@PathVariable long id, Model model){
//        return "users/edit";
//    }

    @GetMapping("/addressUpdate1")
    public String editBillAddress(Address bill_address, Model model, RedirectAttributes redirectAttributes,
                                  @ModelAttribute("ship_address") Address ship_address)
//                              @RequestParam(name = "first_name") String firstName,
//                              @RequestParam(name = "last_name") String lastName,
//                              @RequestParam(name = "address_type") String type,
//                              @RequestParam(name = "street_1") String street1,
//                              @RequestParam(name = "street_2") String street2,
//                              @RequestParam(name = "city") String city,
//                              @RequestParam(name = "zipcode") int zipcode,
//                              @RequestParam(name = "state") String state)

    {
//        address.setAddresstype(type);
//        address.setFirstname(firstName);
//        address.setLastname(lastName);
//        address.setStreet1(street1);
//        address.setStreet2(street2);
//        address.setCity(city);
//        address.setState(state);
//        address.setZipcode(zipcode);
        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.users.getOne(person.getId());
        bill_address.setUser(user);
        bill_address.setAddresstype("Billing");
        addressRepository.save(bill_address);
        bill_address = addressRepository.findByUserAndAddresstype(user, "Billing");
        redirectAttributes.addFlashAttribute("bill_address", bill_address);
        redirectAttributes.addFlashAttribute("ship_address", ship_address);
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:users/profile";
    }

    @GetMapping("/addressUpdate2")

    public String editShipAddress(Address ship_address, RedirectAttributes redirectAttributes,
                                  @ModelAttribute("bill_address") Address bill_address)
//                              @RequestParam(name = "first_name") String firstName,
//                              @RequestParam(name = "last_name") String lastName,
//                              @RequestParam(name = "address_type") String type,
//                              @RequestParam(name = "street_1") String street1,
//                              @RequestParam(name = "street_2") String street2,
//                              @RequestParam(name = "city") String city,
//                              @RequestParam(name = "zipcode") int zipcode,
//                              @RequestParam(name = "state") String state)

    {
//        address.setAddresstype(type);
//        address.setFirstname(firstName);
//        address.setLastname(lastName);
//        address.setStreet1(street1);
//        address.setStreet2(street2);
//        address.setCity(city);
//        address.setState(state);
//        address.setZipcode(zipcode);
        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.users.getOne(person.getId());
        ship_address.setUser(user);
        ship_address.setAddresstype("Shipping");
        addressRepository.save(ship_address);
        ship_address = addressRepository.findByUserAndAddresstype(user, "Shipping");
        redirectAttributes.addFlashAttribute("bill_address", bill_address);
        redirectAttributes.addFlashAttribute("ship_address", ship_address);
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:users/profile";
    }
//
//    @PostMapping("users/addresses/edit")
//    public String editAddressPost(
//            @PathVariable long id,
//            @RequestParam(name = "first_name") String firstName,
//            @RequestParam(name = "last_name") String lastName,
//            @RequestParam(name = "address_type") String type,
//            @RequestParam(name = "street_1") String street1,
//            @RequestParam(name = "street_2") String street2,
//            @RequestParam(name = "city") String city,
//            @RequestParam(name = "zipcode") int zipcode,
//            @RequestParam(name = "state") String state) {
//        Address address = addressRepository.getOne(id);
//        address.setAddresstype(type);
//        address.setFirstname(firstName);
//        address.setLastname(lastName);
//        address.setStreet1(street1);
//        address.setStreet2(street2);
//        address.setCity(city);
//        address.setState(state);
//        address.setZipcode(zipcode);
//        addressRepository.save(address);
//        return "redirect:/users/profile";
//    }


}

//package com.capstone.ecommerce.controllers;
//
//
//
//import com.capstone.ecommerce.model.ShoppingCart;
//
//import com.capstone.ecommerce.model.Address;
//
//import com.capstone.ecommerce.model.User;
//import com.capstone.ecommerce.repositories.AddressRepository;
//import com.capstone.ecommerce.repositories.UserRepository;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
////Tells controller where to get info from
//
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//
//import java.util.List;
//
////Tells controller where to get info from
//
//@Controller
//@SessionAttributes("products")
//public class UserController {
//    private UserRepository users;
//        private PasswordEncoder passwordEncoder;
//    private AddressRepository addressRepository;
//
//        //Stores info in variable so it can be used elsewhere, allows information to be malleable
//
//
//
//
//
//    //Stores info in variable so it can be used elsewhere, allows information to be malleable
//
//    public UserController(UserRepository users, PasswordEncoder passwordEncoder, AddressRepository addyRepo) {
//        this.users = users;
//        this.passwordEncoder = passwordEncoder;
//        this.addressRepository = addyRepo;
//    }
//
//    @GetMapping("/register")
//    public String showSignupForm(Model model){
//        if(model.getAttribute("products") == null) {
//            ShoppingCart products = new ShoppingCart();
//            model.addAttribute("products", products);
//        }
//        model.addAttribute("user", new User());
//        return "users/register";
//    }
//
//
//    @PostMapping("/register")
//    public String saveUser(@ModelAttribute User user) {
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        user.setAdmin(false);
//        users.save(user);
//        return "redirect:/login";
//    }
//
//
////        @GetMapping("/users/profile/{name}")
////        public String findByUsername(@PathVariable String name, Model model) {
////            model.addAttribute("name", name);
////
////
////            return "/users/profile";
////        }
//
//        @GetMapping("/users/profile")
//        public String basicProfile(Model model) {
//            User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            User user = this.users.getOne(person.getId());
//            model.addAttribute("user", user);
//            List<Address> addresses = addressRepository.findByUserId(user.getId());
//            model.addAttribute("addresses", addresses);
//            return "users/profile";
//        }
//
//    @GetMapping("/addresses")
//    public String showAddAddress(@ModelAttribute("user") User user, Model model) {
//        model.addAttribute("user", user);
//        return "users/addresses";
//    }
//
//    @PostMapping("/addresses")
//    public String addAddressPost(@RequestParam(name = "first_name") String firstName,
//                                 @RequestParam(name = "last_name") String lastName,
//                                 @RequestParam(name = "address_type") String type,
//                                 @RequestParam(name = "street_1") String street1,
//                                 @RequestParam(name = "street_2") String street2,
//                                 @RequestParam(name = "city") String city,
//                                 @RequestParam(name = "zipcode") int zipcode,
//                                 @RequestParam(name = "state") String state,
//                                 @ModelAttribute("user") User user) {
//        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User tempUser = this.users.getOne(person.getId());
//        Address address = new Address();
//        address.setAddresstype(type);
//        address.setFirstname(firstName);
//        address.setLastname(lastName);
//        address.setStreet1(street1);
//        address.setStreet2(street2);
//        address.setCity(city);
//        address.setState(state);
//        address.setZipcode(zipcode);
//        address.setUser(tempUser);
//        addressRepository.save(address);
//
//        return "redirect:users/profile";
//    }
//
//    @GetMapping("/users/addresses/edit/{id}")
//    public String getDeleteProfileForm(@PathVariable long id, Model model){
//        return "users/edit";
//    }
//
//    @PostMapping("/users/addresses/edit/")
//    public String editProfile(@ModelAttribute Address address,
//                              @RequestParam(name = "first_name") String firstName,
//                              @RequestParam(name = "last_name") String lastName,
//                              @RequestParam(name = "address_type") String type,
//                              @RequestParam(name = "street_1") String street1,
//                              @RequestParam(name = "street_2") String street2,
//                              @RequestParam(name = "city") String city,
//                              @RequestParam(name = "zipcode") int zipcode,
//                              @RequestParam(name = "state") String state)
//
//    {
//        address.setAddresstype(type);
//        address.setFirstname(firstName);
//        address.setLastname(lastName);
//        address.setStreet1(street1);
//        address.setStreet2(street2);
//        address.setCity(city);
//        address.setState(state);
//        address.setZipcode(zipcode);
//        addressRepository.save(address);
//        return "redirect:/users/profile";
//    }
//
////
////    @PostMapping("users/addresses/edit")
////    public String editAddressPost(
////            @PathVariable long id,
////            @RequestParam(name = "first_name") String firstName,
////            @RequestParam(name = "last_name") String lastName,
////            @RequestParam(name = "address_type") String type,
////            @RequestParam(name = "street_1") String street1,
////            @RequestParam(name = "street_2") String street2,
////            @RequestParam(name = "city") String city,
////            @RequestParam(name = "zipcode") int zipcode,
////            @RequestParam(name = "state") String state) {
////        Address address = addressRepository.getOne(id);
////        address.setAddresstype(type);
////        address.setFirstname(firstName);
////        address.setLastname(lastName);
////        address.setStreet1(street1);
////        address.setStreet2(street2);
////        address.setCity(city);
////        address.setState(state);
////        address.setZipcode(zipcode);
////        addressRepository.save(address);
////        return "redirect:/users/profile";
////    }
//
//
//}