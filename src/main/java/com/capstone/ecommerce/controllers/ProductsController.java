package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Categories;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.ShoppingCart;
//import com.capstone.ecommerce.repositories.CategoriesRepository;
import com.capstone.ecommerce.repositories.ProductRepository;
import com.capstone.ecommerce.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("products")
public class ProductsController {
    private ProductRepository productRepo;

    private ProductRepository imagesRepo;

//  CONSTRUCTOR
    public ProductsController(ProductRepository productRepo, ProductRepository imagesRepo) {
        this.productRepo = productRepo;
        this.imagesRepo = imagesRepo;
    }

    @GetMapping("/products/all")
    public String productsIndex(Model model) {
        if(model.getAttribute("products") == null) {
            ShoppingCart blankProducts = new ShoppingCart();
            model.addAttribute("products", blankProducts);
        }
        List<Product> allProducts = productRepo.findAll();

        model.addAttribute("showProducts", allProducts);
        return "products/products";
    }

//    @GetMapping("products/t-shirts")
//    public String viewTshirts(Model model) {
//        model.addAttribute("products", productRepo.findAll());
//        return "products/t-shirts";
//    }
//
//    @GetMapping("products/pullover")
//    public String viewPullover(Model model) {
//        model.addAttribute("products", productRepo.findAll());
//        return "products/pullover";
//    }
//
//    @GetMapping("products/hats")
//    public String viewHats(Model model) {
//        model.addAttribute("products", productRepo.findAll());
//        return "products/hats";
//    }

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

    @GetMapping("/products/{id}")
    public String singleProduct(Model model, @PathVariable long id){
        Product aProduct = productRepo.getOne(id);
        model.addAttribute("product", aProduct);
        List<Categories> categories = aProduct.getCategories();
        String cNames = "";
        for(Categories category: categories){
            cNames = cNames + category.getCategory() + ", ";
        }
        cNames = StringUtils.chop(cNames);
        cNames = StringUtils.chop(cNames);
        model.addAttribute("categories", cNames);
        return "products/product";
    }

    //  SEARCH
    @PostMapping("/products/search")
    public String searchProduct(@RequestParam (name = "keyword") String keyword,
                                @RequestParam (name = "choice") String choice,
                                Model model) {
        List<Product> chosenProducts = new ArrayList<>();
        int i = 1;
        if(choice.equals("name")){
            chosenProducts = productRepo.findByNameContaining(keyword);
        } else if (choice.equals("category")){
            chosenProducts = productRepo.findByCategoriesContaining(keyword);
//            for(i = 0; i < chosenProducts.size(); i++){
//              for(Product product : chosenProducts){
//                    if(chosenProducts.get(i).getId() == product.getId()){
//                        chosenProducts.remove(chosenProducts.get(i));
//                    }
//                }
//            }
        }

        model.addAttribute("showProducts", chosenProducts);
        return "products/products";
      
    public String searchProduct(@RequestParam (name = "keyword") String keyword, Model model) {
        List<Product> products = productRepo.findByNameContaining(keyword);
        model.addAttribute("products", products);
        return "products/index";
    }


}
