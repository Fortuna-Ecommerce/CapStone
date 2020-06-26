package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Categories;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.ShoppingCart;
import com.capstone.ecommerce.model.User;
import com.capstone.ecommerce.repositories.ProductRepository;
import com.capstone.ecommerce.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

//import com.capstone.ecommerce.repositories.CategoriesRepository;
@Controller
@SessionAttributes({"products", "category", "user"})
public class ProductsController {
    private UserRepository userRepo;
    private ProductRepository productRepo;
    private final NumberFormat currencyFormat=new DecimalFormat("#0.00");

    //  CONSTRUCTOR
    public ProductsController(ProductRepository productRepo, UserRepository userRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }


    @GetMapping("/products/all")
    public String productsIndex(Model model) {
        if (model.getAttribute("products") == null) {
            ShoppingCart blankProducts = new ShoppingCart();
            model.addAttribute("products", blankProducts);
        }
        if (model.getAttribute("category") != null || model.getAttribute("category") != "") {
            String category = "";
            model.addAttribute("category", category);
        }

        List<Product> showProducts = productRepo.findbySizeandColor("MD", "000000");
        List<Product> showProducts2 = productRepo.findbySizeandColor("MD", "808080");
        List<Product> showProducts3 = productRepo.findbySizeandColor("MD", "FFFFFF");
        List<Product> hatProducts = productRepo.findbySizeandColor("OSFM", "000000");
        showProducts.addAll(hatProducts);
        showProducts.addAll(showProducts2);
        showProducts.addAll(showProducts3);


        Collections.shuffle(showProducts);



        model.addAttribute("showProducts", showProducts);

        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            User user = new User();
            model.addAttribute("user", user);
            return "products/products";
        }
        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User testShopper = this.userRepo.getOne(shopper.getId());
        model.addAttribute("user", testShopper);

        return "products/products";
    }

    @GetMapping("/products/t-shirts")
    public String viewTshirts(Model model) {
        if (model.getAttribute("category") != null || model.getAttribute("category") != "") {
            String category = "";
            model.addAttribute("category", category);
        }
//        List<Product> allProducts = productRepo.findAll();
        List<Product> allProducts1 = productRepo.findbySizeandColor("XS", "808080");
        List<Product> allProducts2 = productRepo.findbySizeandColor("XS", "000000");
        List<Product> allProducts3 = productRepo.findbySizeandColor("XS", "FFFFFF");
        //        for (Product product : allProducts) {
//            if (product.getType().equals("Shirt")) {
//                chosenProducts.add(product);
//            }
//        }

        List<Product> chosenProducts = new ArrayList<>(allProducts1);
        chosenProducts.addAll(allProducts2);
        chosenProducts.addAll(allProducts3);
        Collections.shuffle(chosenProducts);

//
        Product product27 = productRepo.getOne(27L);
        Product product28 = productRepo.getOne(28L);
        model.addAttribute("product27", product27);
        model.addAttribute("product28", product28);
//

        model.addAttribute("showProducts", chosenProducts);
        return "products/products";
    }


    @GetMapping("/products/pullovers")
    public String viewPullover(Model model) {
        if (model.getAttribute("category") != null || model.getAttribute("category") != "") {
            String category = "";
            model.addAttribute("category", category);
        }

        List<Product> allProducts1 = productRepo.findbySizeandColor("MD", "000000");
        List<Product> allProducts2 = productRepo.findbySizeandColor("MD", "808080");
        List<Product> allProducts3 = productRepo.findbySizeandColor("MD", "FFFFFF");
        List<Product> chosenProducts = new ArrayList<>();
        for (Product product : allProducts1) {
            if (product.getType().equals("Pullover")) {
                chosenProducts.add(product);
            }
        }
        for (Product product : allProducts2) {
            if (product.getType().equals("Pullover")) {
                chosenProducts.add(product);
            }
        }
        for (Product product : allProducts3) {
            if (product.getType().equals("Pullover")) {
                chosenProducts.add(product);
            }
        }

        Collections.shuffle(chosenProducts);

//
        Product product27 = productRepo.getOne(27L);
        Product product28 = productRepo.getOne(28L);
        model.addAttribute("product27", product27);
        model.addAttribute("product28", product28);
//

        model.addAttribute("showProducts", chosenProducts);
        return "products/products";
    }


    @GetMapping("/products/hats")
    public String viewHats(Model model) {
        if (model.getAttribute("category") != null || model.getAttribute("category") != "") {
            String category = "";
            model.addAttribute("category", category);
        }
        List<Product> chosenProducts = productRepo.findBySize("OSFM");
//        List<Product> chosenProducts = new ArrayList<>();
//        for (Product product : allProducts) {
//            if (product.getType().equals("Hat")) {
//                chosenProducts.add(product);
//            }
//        }

//
        Product product27 = productRepo.getOne(27L);
        Product product28 = productRepo.getOne(28L);
        model.addAttribute("product27", product27);
        model.addAttribute("product28", product28);
//
        Collections.shuffle(chosenProducts);
        model.addAttribute("showProducts", chosenProducts);
        return "products/products";
    }



    @GetMapping("/products/{id}")
    public String singleProduct(Model model, @PathVariable long id) {
        if (model.getAttribute("products") == null) {
            ShoppingCart blankProducts = new ShoppingCart();
            model.addAttribute("products", blankProducts);
        }
        String imageColor = "";
        double tempSalePrice;
        String salePrice = "";

        Product aProduct = productRepo.getOne(id);
        model.addAttribute("product", aProduct);
        String realPrice = currencyFormat.format(aProduct.getPrice());
        String black = "";
        String white = "";
        String gray = "";
        Product tempA = new Product();
        Product tempB = new Product();
        if(aProduct.getColor().equals("FFFFFF")){
            white = aProduct.getProductImage();
            tempA = productRepo.findByNameAndSizeAndColorAndType(aProduct.getName(),aProduct.getSize(), "000000",
                    aProduct.getType());
            black = tempA.getProductImage();
            tempB = productRepo.findByNameAndSizeAndColorAndType(aProduct.getName(), aProduct.getSize(),"808080", aProduct.getType());
            gray = tempB.getProductImage();
        } else if (aProduct.getColor().equals("000000")){
            black = aProduct.getProductImage();
            tempA = productRepo.findByNameAndSizeAndColorAndType(aProduct.getName(),aProduct.getSize(), "FFFFFF", aProduct.getType());
            white = tempA.getProductImage();
            tempB = productRepo.findByNameAndSizeAndColorAndType(aProduct.getName(),aProduct.getSize(), "808080",
                    aProduct.getType());
            gray = tempB.getProductImage();
        } else if (aProduct.getColor().equals("808080")){
            gray = aProduct.getProductImage();
            tempA = productRepo.findByNameAndSizeAndColorAndType(aProduct.getName(), aProduct.getSize(),"000000", aProduct.getType());
            black = tempA.getProductImage();
            tempB = productRepo.findByNameAndSizeAndColorAndType(aProduct.getName(), aProduct.getSize(),"FFFFFF", aProduct.getType());
            white = tempB.getProductImage();
        }
        model.addAttribute("white", white);
        model.addAttribute("black", black);
        model.addAttribute("gray", gray);
        if(aProduct.getType().equals("Hat")){
            imageColor = "808080";
        } else {
            imageColor = "E6E6E6";
        }

        if (aProduct.getSpecial() != null && aProduct.getSpecial()) {
            tempSalePrice = aProduct.getPrice() - (aProduct.getPrice() * 0.42);
            tempSalePrice = Math.round(tempSalePrice * 100.00) / 100.00;
            salePrice = currencyFormat.format(tempSalePrice);

        }
        model.addAttribute("realPrice", realPrice);
        model.addAttribute("salePrice", salePrice);
        model.addAttribute("imageColor", imageColor);
        return "products/product";
    }

    @PostMapping("/products/search")
    public String searchProduct(@RequestParam(name = "keyword") String keyword,
                                Model model,
                                RedirectAttributes redirectAttributes) {
//        List<Product> chosenProducts = new ArrayList<>();
//            chosenProducts = productRepo.findByNameContaining(keyword);
            List<Product> chosenProducts = productRepo.findBySizeAndNameContaining("MD", keyword);
            List<Product> foundHats = productRepo.findBySizeAndNameContaining("OSFM", keyword);
            chosenProducts.addAll(foundHats);
//        LinkedHashSet<Product> theChosen = new LinkedHashSet<>(chosenProducts);
//        chosenProducts.clear();
//        chosenProducts.addAll(theChosen);

        redirectAttributes.addFlashAttribute("showProducts", chosenProducts);
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            User user = new User();
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:search";
        }
        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User testShopper = this.userRepo.getOne(shopper.getId());
        redirectAttributes.addFlashAttribute("user", testShopper);
        return "redirect:search";
    }


    @GetMapping("/products/search")
    public String searchProductLander(Model model,
                                      @ModelAttribute("showProducts") ArrayList<Product> showProducts) {
        model.addAttribute("showProducts", showProducts);
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            User user = new User();
            model.addAttribute("user", user);
            return "products/products";
        }
        User shopper = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User testShopper = this.userRepo.getOne(shopper.getId());
        model.addAttribute("user", testShopper);
        return "products/products";
    }

    @GetMapping("/products/display={category}")
    public String categorySearch(Model model, @PathVariable String category) {
        List<Product> chosenProducts = new ArrayList<>();
        chosenProducts = productRepo.findByCategoriesContaining(category);
        LinkedHashSet<Product> theChosen = new LinkedHashSet<>(chosenProducts);
        chosenProducts.clear();
        chosenProducts.addAll(theChosen);
        model.addAttribute("category", category);
        model.addAttribute("showProducts", chosenProducts);

        return "products/products";
    }


//    public void main(String[] args) {
//        seedProducts();
//    }

//    public void main(String[] args) {
//        seedProducts();
//    }

    //  SEARCH
//    @PostMapping("/products/search")
//
//    public String searchProduct(@RequestParam(name = "keyword") String keyword, Model model) {
//        List<Product> products = productRepo.findByNameContaining(keyword);
//        model.addAttribute("products", products);
//        return "products/index";
//    }
}
