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

import java.util.*;

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
        List<Product> chosenProducts = productRepo.findbySizeandColor("XS", "FFFFFF");
//        for (Product product : allProducts) {
//            if (product.getType().equals("Shirt")) {
//                chosenProducts.add(product);
//            }
//        }

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
        List<Product> allProducts = productRepo.findbySizeandColor("MD", "FFFFFF");
        List<Product> chosenProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getType().equals("Pullover")) {
                chosenProducts.add(product);
            }
        }

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
        List<Product> chosenProducts = productRepo.findbySizeandColor("OSFM", "000000");
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
        String imageColor = "";
        double salePrice = 0;
        Product aProduct = productRepo.getOne(id);
        model.addAttribute("product", aProduct);
        System.out.println(aProduct.getName());
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
        model.addAttribute("imageColor", imageColor);
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
