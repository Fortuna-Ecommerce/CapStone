package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.ProductImages;
import com.capstone.ecommerce.repositories.ProductRepository;
import com.capstone.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductsController {
    private ProductRepository productRepo;
//  CONSTRUCTOR
    public ProductsController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/products/all")
    public String productsIndex(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("allProducts", products);
        return "products/products";
    }

    @GetMapping("products/t-shirts")
    public String viewTshirts(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products/t-shirts";
    }

    @GetMapping("products/pullover")
    public String viewPullover(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products/pullover";
    }

    @GetMapping("products/hats")
    public String viewHats(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products/hats";
    }

    @GetMapping("products/t-shirts/{id}")
    public String individualTshirt(Model model, @PathVariable("id") long id) {
        Product tshirt = productRepo.getOne(id);
        model.addAttribute("tshirt", tshirt);
        return "products/t-shirts/show";
    }

    @GetMapping("products/pullover/{id}")
    public String individualPullover(Model model, @PathVariable long id) {
        Product aProduct = productRepo.getOne(id);
        model.addAttribute("pullover", aProduct);
        return "products/pullover/show";
    }

    @GetMapping("products/hats/{id}")
    public String individualHat(Model model, @PathVariable long id) {
        Product aProduct = productRepo.getOne(id);
        model.addAttribute("hat", aProduct);
        return "products/hats/show";
    }

    //  SEARCH
    @PostMapping("/products/search")
    public String searchProduct(@RequestParam (name = "keyword") String keyword, Model model) {
        List<Product> products = productRepo.findByNameContaining(keyword);
        model.addAttribute("products", products);
        return "products/index";
    }

    public ProductImages getImage(@PathVariable String path, Model model) {
        ProductImages image = productRepo.getFilePath(path);
        model.addAttribute("path", path);
        return image;
    }



}
