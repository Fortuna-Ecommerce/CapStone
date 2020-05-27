package com.capstone.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class UserController {

    public UserController() {
    }

    @GetMapping("/profile")
    @ResponseBody
    public String User() {
        return "/profile";
    }

    @GetMapping("/user/{id}/profile/edit")
    @ResponseBody
    public String editProfile(){
        return "/user/{id}/profile/edit";
    }

    @GetMapping("/user/profile/{id}")
    @ResponseBody
    public String viewProfile(){
        return "user/profile/{id}";
    }

    @GetMapping("/user/products/tshirts/{id}/review")
    @ResponseBody
    public String productReview(){
        return "/user/products/tshirts/{id}/review";
    }

    @GetMapping("/user/products")
    @ResponseBody
    public String products(){
        return "/user/products";
    }

    @GetMapping("/user/products/tshirts/{id}")
    @ResponseBody
    public String sortTshirtsById(){
        return "/user/products/tshirts/{id}";
    }

    @GetMapping("/user/products/tshirts/price/{id}")
    @ResponseBody
    public String sortTshirtsByPrice(){
        return "/user/products/tshirts/price/{id}";
    }

    @GetMapping("/user/products/tshirts/category/{id}")
    @ResponseBody
    public String sortTshirtsByCategory(){
        return "/user/products/tshirts/category/{id}";
    }

    @GetMapping("/user/products/tshirts/reviews")
    @ResponseBody
    public String sortTshirtsByReviews(){
        return "/user/products/tshirts/reviews";
    }

    @GetMapping("/user/products/tshirts")
    @ResponseBody
    public String viewAllTshirts(){
        return "/user/products/tshirts";
    }

    @GetMapping("/user/products/hats/{id}")
    @ResponseBody
    public String sortHatsById(){
        return "/user/products/hats/{id}";
    }

    @GetMapping("/user/products/tshirts/price/{id}")
    @ResponseBody
    public String sortHatsByPrice(){
        return "/user/products/hats/price/{id}";
    }

    @GetMapping("/user/products/hats/category/{id}")
    @ResponseBody
    public String sortHatsByCategory(){
        return "/user/products/hats/category/{id}";
        }

    @GetMapping("/user/products/hats/reviews")
    @ResponseBody
    public String sortHatsByReviews(){
        return "/user/products/hats/reviews";
    }

    @GetMapping("/user/products/hats")
    @ResponseBody
    public String viewAllHats(){
        return "/user/products/hats";
    }

}
