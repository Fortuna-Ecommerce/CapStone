package com.capstone.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class jsForLoop2Controller {

    @GetMapping("/js")
    public String js() {
        return "jsForLoop2";
    }

}