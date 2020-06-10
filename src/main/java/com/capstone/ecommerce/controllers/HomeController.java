package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.ShoppingCart;
import com.capstone.ecommerce.model.User;
import com.capstone.ecommerce.repositories.ProductRepository;
import com.capstone.ecommerce.repositories.TransactionProductRepository;
import com.capstone.ecommerce.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@SessionAttributes({"products", "category", "user"})
public class HomeController {
    private ProductRepository productRepo;
//    private ProductImagesRepository productImagesRepo;
    private final UserRepository userRepo;
    private final TransactionProductRepository transProdRepo;


    public HomeController(ProductRepository productRepo, UserRepository userRepo, TransactionProductRepository transProdRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.transProdRepo = transProdRepo;
    }



    @GetMapping("/aboutUs")
    public String aboutUs() {

        return "aboutUs";
    }

    @GetMapping("/")
    public String welcome(Model model) {

        if(model.getAttribute("products") == null) {
            ShoppingCart products = new ShoppingCart();
            model.addAttribute("products", products);
        }
        if (model.getAttribute("category") == null) {
            String category = "";
            model.addAttribute("category", category);
        }
//      MADE CHANGE HERE
//        List<Product> allProducts = productRepo.findAll();
;
        Product product3 = productRepo.getOne(3L);
        Product product4 = productRepo.getOne(16L);
        Product product5 = productRepo.getOne(32L);
        Product product6 = productRepo.getOne(48L);
        Product product7 = productRepo.getOne(61L);
        Product product8 = productRepo.getOne(76L);
        Product product9 = productRepo.getOne(95L);
        Product product10 = productRepo.getOne(10L);
        Product product11 = productRepo.getOne(39L);

        model.addAttribute("product3", product3);
        model.addAttribute("product4", product4);
        model.addAttribute("product5", product5);
        model.addAttribute("product6", product6);
        model.addAttribute("product7", product7);
        model.addAttribute("product8", product8);
        model.addAttribute("product9", product9);
        model.addAttribute("product10", product10);
        model.addAttribute("product11", product11);

        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            User user = new User();
            model.addAttribute("user", user);
            return "home";
        }
        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User testShopper = this.userRepo.getOne(shopper.getId());
        model.addAttribute("user", testShopper);
//        model.addAttribute("showProducts", allProducts);

        return "home";
    }

    @GetMapping("products/productInventory")
    public String getProducts(Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "home";
        }
        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepo.getOne(person.getId());
        if(user.getAdmin()){
//            ProductImages prodImg = productImagesRepo.getOne((long) 1);
//            model.addAttribute("image", prodImg);
            model.addAttribute("user", user);
            List<Product> products = productRepo.findAll();
            Collections.reverse(products);
            model.addAttribute("allProducts", products);
            return "products/productInventory";
        }
      return "home";
    }

//  ADD

    @GetMapping("products/productInventory/add")
    public String showAddProduct(Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "home";
        }
        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepo.getOne(person.getId());
        if(user.getAdmin()){
            model.addAttribute("user", user);
            return "products/productInventory";
        }
        return "home";
    }

    @PostMapping("products/productInventory/add")
    public String addProductPost(@RequestParam (name = "name") String name,
                                 @RequestParam (name = "color") String color,
                                 @RequestParam(name = "special") boolean special,
                                 @RequestParam(name = "desc") String description,
                                 @RequestParam (name = "size") String size,
                                 @RequestParam (name = "type") String type,
                                 @RequestParam (name = "price") float price,
                                 @RequestParam (name = "quan") long quan,
                                 @RequestParam (name = "image") String image,
                                 Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "home";
        }
        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepo.getOne(person.getId());
        if(user.getAdmin()){
            model.addAttribute("user", user);
            Product product = new Product();
            product.setName(name);
            product.setSpecial(special);
            product.setColor(color);
            product.setDescription(description);
            product.setSize(size);
            product.setType(type);
            product.setPrice(price);
            product.setQuantity(quan);
            product.setProductImage(image);
            productRepo.save(product);
            return "redirect:/products/productInventory";
        }
        return "home";
    }

//  DELETE
    @Transactional
    @PostMapping("productInventory/delete")
    public String deleteProductPost(@RequestParam (name = "deleteId") long id, Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "home";
        }
        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepo.getOne(person.getId());
        Product product = productRepo.findById(id);
        if (user.getAdmin()) {
            model.addAttribute("user", user);
//            transProdRepo.deleteByProductId(id);
            transProdRepo.deleteByProduct(product);
            productRepo.deleteById(id);
            return "redirect:/products/productInventory";
        }
        return "home";
    }

//        @PostMapping("products/productInventory/add")
//        public String addProductPost(@RequestParam (name = "name") String name,
//                @RequestParam (name = "color") String color,
//                @RequestParam (name = "size") String size,
//                @RequestParam (name = "type") String type,
//        @RequestParam (name = "price") float price,
//        @RequestParam (name = "quan") long quan,
//        @RequestParam (name = "image") String image) {
//            Product product = new Product();
//            product.setName(name);
//            product.setColor(color);
//            product.setSize(size);
//            product.setType(type);
//            product.setPrice(price);
//            product.setQuantity(quan);
//            product.setImage(image);
//            productRepo.save(product);
//            return "redirect:/products/productInventory";
//        }

//  EDIT
    @GetMapping("/productsInventory/edit/{id}")
    public String editProductForm(@PathVariable long id, Model model) {
            model.addAttribute("product", productRepo.getOne(id));
            return "products/productInventory";
    }

    @PostMapping("/productsInventory/edit/{id}")
    public String editProduct(
                              @PathVariable long id,
                              @RequestParam (name = "name") String name,
                              @RequestParam (name = "color") String color,
                              @RequestParam (name = "size") String size,
                              @RequestParam (name = "type") String type,
                              @RequestParam (name = "price") float price,
                              @RequestParam (name = "quan") long quan) {
        Product product = productRepo.getOne(id);
        product.setName(name);
        product.setColor(color);
        product.setSize(size);
        product.setType(type);
        product.setPrice(price);
        product.setQuantity(quan);
        productRepo.save(product);
        return "redirect:/products/productInventory";
    }


    //  DELETE
    @GetMapping("/productsInventory/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productRepo.deleteById(id);
        return "redirect:/products/productInventory";
    }


//  SEARCH
    @PostMapping("productsInventory/search")
    public String searchProduct(@RequestParam (name = "keyword") String keyword, Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "home";
        }
        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepo.getOne(person.getId());
        if(user.getAdmin()){
            model.addAttribute("user", user);
            List<Product> products = productRepo.findByNameContaining(keyword);
            model.addAttribute("allProducts", products);
            return "products/productInventory";
        }
        return "home";
    }
}
