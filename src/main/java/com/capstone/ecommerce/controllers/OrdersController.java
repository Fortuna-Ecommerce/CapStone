package com.capstone.ecommerce.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrdersController {

        @GetMapping("/orders")
        @ResponseBody
        public String order() {
            return "Here are your orders!";
        }

}
