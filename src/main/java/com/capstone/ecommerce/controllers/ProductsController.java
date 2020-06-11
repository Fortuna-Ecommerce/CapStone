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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
//import com.capstone.ecommerce.repositories.CategoriesRepository;
@Controller
@SessionAttributes({"products", "category", "user"})
public class ProductsController {
    private UserRepository userRepo;
    private ProductRepository productRepo;

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

        List<Product> showProducts = productRepo.findbySizeandColor("MD", "FFFFFF");
        List<Product> hatProducts = productRepo.findbySizeandColor("OSFM", "FFFFFF");
        showProducts.addAll(hatProducts);
//
//        List<Product> allProducts = productRepo.findAll();
//        Product product3 = productRepo.getOne(3L);
//        Product product4 = productRepo.getOne(16L);
//        Product product5 = productRepo.getOne(32L);
//        Product product6 = productRepo.getOne(48L);
//        Product product7 = productRepo.getOne(61L);
//        Product product8 = productRepo.getOne(76L);
//        Product product9 = productRepo.getOne(95L);
//        Product product10 = productRepo.getOne(10L);
//        Product product11 = productRepo.getOne(11L);
//        Product product12 = productRepo.getOne(12L);
//        Product product13 = productRepo.getOne(13L);
//        Product product14 = productRepo.getOne(14L);
//        Product product15 = productRepo.getOne(15L);
//        Product product16 = productRepo.getOne(16L);
//        Product product17 = productRepo.getOne(17L);
//        Product product18 = productRepo.getOne(18L);
//        Product product19 = productRepo.getOne(19L);
//        Product product20 = productRepo.getOne(20L);
//        Product product21 = productRepo.getOne(21L);
//        Product product22 = productRepo.getOne(22L);
//        Product product23 = productRepo.getOne(23L);
//        Product product24 = productRepo.getOne(24L);
//        Product product25 = productRepo.getOne(25L);
//        Product product26 = productRepo.getOne(26L);
//        Product product27 = productRepo.getOne(27L);
//        Product product28 = productRepo.getOne(28L);
//        Product product29 = productRepo.getOne(29L);
//        Product product30 = productRepo.getOne(30L);
//        Product product31 = productRepo.getOne(31L);
//        Product product32 = productRepo.getOne(32L);
//
//        model.addAttribute("product3", product3);
//        model.addAttribute("product4", product4);
//        model.addAttribute("product5", product5);
//        model.addAttribute("product6", product6);
//        model.addAttribute("product7", product7);
//        model.addAttribute("product8", product8);
//        model.addAttribute("product9", product9);
//        model.addAttribute("product10", product10);
//        model.addAttribute("product11", product11);
//        model.addAttribute("product12", product12);
//        model.addAttribute("product13", product13);
//        model.addAttribute("product14", product14);
//        model.addAttribute("product15", product15);
//        model.addAttribute("product16", product16);
//        model.addAttribute("product17", product17);
//        model.addAttribute("product18", product18);
//        model.addAttribute("product19", product19);
//        model.addAttribute("product20", product20);
//        model.addAttribute("product21", product21);
//        model.addAttribute("product22", product22);
//        model.addAttribute("product23", product23);
//        model.addAttribute("product24", product24);
//        model.addAttribute("product25", product25);
//        model.addAttribute("product26", product26);
//        model.addAttribute("product27", product27);
//        model.addAttribute("product28", product28);
//        model.addAttribute("product29", product29);
//        model.addAttribute("product30", product30);
//        model.addAttribute("product31", product31);
//        model.addAttribute("product32", product32);


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
        List<Product> chosenProducts = productRepo.findbySizeandColor("XS", "FFFFFF");
//        for (Product product : allProducts) {
//            if (product.getType().equals("Shirt")) {
//                chosenProducts.add(product);
//            }
//        }
        model.addAttribute("showProducts", chosenProducts);
        return "products/products";
    }


    @GetMapping("/products/pullovers")
    public String viewPullover(Model model) {
        if (model.getAttribute("category") != null || model.getAttribute("category") != "") {
            String category = "";
            model.addAttribute("category", category);
        }
        List<Product> allProducts = productRepo.findbySizeandColor("MD", "FFFFFF");
        List<Product> chosenProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getType().equals("Pullover")) {
                chosenProducts.add(product);
            }
        }
        model.addAttribute("showProducts", chosenProducts);
        return "products/products";
    }


    @GetMapping("/products/hats")
    public String viewHats(Model model) {
        if (model.getAttribute("category") != null || model.getAttribute("category") != "") {
            String category = "";
            model.addAttribute("category", category);
        }
        List<Product> chosenProducts = productRepo.findbySizeandColor("OSFM", "000000");
//        List<Product> chosenProducts = new ArrayList<>();
//        for (Product product : allProducts) {
//            if (product.getType().equals("Hat")) {
//                chosenProducts.add(product);
//            }
//        }
        model.addAttribute("showProducts", chosenProducts);
        return "products/products";
    }

//    @GetMapping("products/t-shirts/{id}")
//    public String individualTshirt(Model model, @PathVariable("id") long id) {
//        Product tshirt = productRepo.getOne(id);
//        model.addAttribute("tshirt", tshirt);
//        return "products/t-shirts/show";
//    }
//
//    @GetMapping("products/pullover/{id}")
//    public String individualPullover(Model model, @PathVariable long id) {
//        Product aProduct = productRepo.getOne(id);
//        model.addAttribute("pullover", aProduct);
//        return "products/pullover/show";
//    }
//
//    @GetMapping("products/hats/{id}")
//    public String individualHat(Model model, @PathVariable long id) {
//        Product aProduct = productRepo.getOne(id);
//        model.addAttribute("hat", aProduct);
//        return "products/hats/show";
//    }


//    @GetMapping("products/hats")
//    public String viewHats(Model model) {
//        model.addAttribute("products", productRepo.findAll());
//        return "products/hats";
//    }
//    @GetMapping("products/t-shirts/{id}")
//    public String individualTshirt(Model model, @PathVariable("id") long id) {
//        Product tshirt = productRepo.getOne(id);
//        model.addAttribute("tshirt", tshirt);
//        return "products/t-shirts/show";
//    }
//    @GetMapping("products/pullover/{id}")
//    public String individualPullover(Model model, @PathVariable long id) {
//        Product aProduct = productRepo.getOne(id);
//        model.addAttribute("pullover", aProduct);
//        return "products/pullover/show";
//    }
//    @GetMapping("products/hats/{id}")
//    public String individualHat(Model model, @PathVariable long id) {
//        Product aProduct = productRepo.getOne(id);
//        model.addAttribute("hat", aProduct);
//        return "products/hats/show";
//    }

    @GetMapping("/products/{id}")
    public String singleProduct(Model model, @PathVariable long id) {
        if (model.getAttribute("products") == null) {
            ShoppingCart blankProducts = new ShoppingCart();
            model.addAttribute("products", blankProducts);
        }
        double salePrice = 0;
        Product aProduct = productRepo.getOne(id);
        model.addAttribute("product", aProduct);
        String black = "";
        String white = "";
        String gray = "";
        Product tempA = new Product();
        Product tempB = new Product();
        if(aProduct.getColor().equals("FFFFFF")){
            white = aProduct.getProductImage();
            tempA = productRepo.findByNameAndSizeAndColor(aProduct.getName(),"XL", "000000");
            black = tempA.getProductImage();
            tempB = productRepo.findByNameAndSizeAndColor(aProduct.getName(), "XL","808080");
            gray = tempB.getProductImage();
        } else if (aProduct.getColor().equals("000000")){
            black = aProduct.getProductImage();
            tempA = productRepo.findByNameAndSizeAndColor(aProduct.getName(),"XL", "FFFFFF");
            white = tempA.getProductImage();
            tempB = productRepo.findByNameAndSizeAndColor(aProduct.getName(),"XL", "808080");
            gray = tempB.getProductImage();
        } else if (aProduct.getColor().equals("808080")){
            gray = aProduct.getProductImage();
            tempA = productRepo.findByNameAndSizeAndColor(aProduct.getName(), "XL","000000");
            black = tempA.getProductImage();
            tempB = productRepo.findByNameAndSizeAndColor(aProduct.getName(), "XL","FFFFFF");
            white = tempB.getProductImage();
        }
        model.addAttribute("white", white);
        model.addAttribute("black", black);
        model.addAttribute("gray", gray);
        if (aProduct.getSpecial() != null && aProduct.getSpecial()) {
            salePrice = aProduct.getPrice() - (aProduct.getPrice() * 0.42);
            salePrice = Math.round(salePrice * 100.00) / 100.00;
        }
        model.addAttribute("salePrice", salePrice);
//        List<Categories> categories = aProduct.getCategories();
//        String cNames = "";
//        for (Categories category : categories) {
//            cNames = cNames + category.getCategory() + ", ";
//        }
//        cNames = StringUtils.chop(cNames);
//        cNames = StringUtils.chop(cNames);
//        model.addAttribute("categories", cNames);
        return "products/product";
    }

    @PostMapping("/products/search")
    public String searchProduct(@RequestParam(name = "keyword") String keyword,
                                Model model,
                                RedirectAttributes redirectAttributes) {
//        List<Product> chosenProducts = new ArrayList<>();
//            chosenProducts = productRepo.findByNameContaining(keyword);
            List<Product> chosenProducts = productRepo.findBySizeAndColorAndNameContaining("MD", "FFFFFF", keyword);
            List<Product> foundHats = productRepo.findBySizeAndColorAndNameContaining("OSFM", "FFFFFF", keyword);
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
