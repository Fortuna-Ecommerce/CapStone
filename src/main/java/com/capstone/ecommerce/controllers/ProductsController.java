package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Categories;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.ShoppingCart;
import com.capstone.ecommerce.repositories.ProductRepository;
import org.apache.commons.lang3.StringUtils;
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

    private ProductRepository productRepo;
    private ProductRepository imagesRepo;

    //  CONSTRUCTOR
    public ProductsController(ProductRepository productRepo, ProductRepository imagesRepo) {
        this.productRepo = productRepo;
        this.imagesRepo = imagesRepo;
    }

    public void seedProducts() {
        Product product1 = new Product("Rage meme", "5DADE2", "XL", "Shirt", 22.22, "Angry guy melting down",
                false,
                (long) 5);
        Product product2 = new Product("Pepe punch", "95A5A6", "L", "Shirt", 28.22, "Frog person threatening pose", false,
                (long) 23);
        Product product3 = new Product("Pepe sad", "8E44AD", "XL", "Hoodie", 35.78, "Frog person very down", true, (long) 1000);
        Product product4 = new Product("NPC face", "E74C3C", "OSFM", "Hat", 15.99, "Fellow with straight line mouth and " +
                "angly " +
                "eyebrows", false, (long) 9);
        Product product5 = new Product("Rage meme", "FDFEFE", "S", "Hoodie", 35.99, "Angry guy melting down", false, (long) 0);
        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);
        productRepo.save(product4);
        productRepo.save(product5);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
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
        List<Product> allProducts = productRepo.findAll();
        Product product3 = productRepo.getOne(3L);
        Product product4 = productRepo.getOne(4L);
        Product product5 = productRepo.getOne(5L);
        Product product6 = productRepo.getOne(6L);
        Product product7 = productRepo.getOne(7L);
        Product product8 = productRepo.getOne(8L);
        Product product9 = productRepo.getOne(9L);
        Product product10 = productRepo.getOne(10L);
        Product product11 = productRepo.getOne(11L);
        Product product12 = productRepo.getOne(12L);
        Product product13 = productRepo.getOne(13L);
        Product product14 = productRepo.getOne(14L);
        Product product15 = productRepo.getOne(15L);
        Product product16 = productRepo.getOne(16L);
        Product product17 = productRepo.getOne(17L);
        Product product18 = productRepo.getOne(18L);
        Product product19 = productRepo.getOne(19L);
        Product product20 = productRepo.getOne(20L);
        Product product21 = productRepo.getOne(21L);
        Product product22 = productRepo.getOne(22L);
        Product product23 = productRepo.getOne(23L);
        Product product24 = productRepo.getOne(24L);
        Product product25 = productRepo.getOne(25L);
        Product product26 = productRepo.getOne(26L);
        Product product27 = productRepo.getOne(27L);
        Product product28 = productRepo.getOne(28L);
        Product product29 = productRepo.getOne(29L);
        Product product30 = productRepo.getOne(30L);
        Product product31 = productRepo.getOne(31L);
        Product product32 = productRepo.getOne(32L);

        model.addAttribute("product3", product3);
        model.addAttribute("product4", product4);
        model.addAttribute("product5", product5);
        model.addAttribute("product6", product6);
        model.addAttribute("product7", product7);
        model.addAttribute("product8", product8);
        model.addAttribute("product9", product9);
        model.addAttribute("product10", product10);
        model.addAttribute("product11", product11);
        model.addAttribute("product12", product12);
        model.addAttribute("product13", product13);
        model.addAttribute("product14", product14);
        model.addAttribute("product15", product15);
        model.addAttribute("product16", product16);
        model.addAttribute("product17", product17);
        model.addAttribute("product18", product18);
        model.addAttribute("product19", product19);
        model.addAttribute("product20", product20);
        model.addAttribute("product21", product21);
        model.addAttribute("product22", product22);
        model.addAttribute("product23", product23);
        model.addAttribute("product24", product24);
        model.addAttribute("product25", product25);
        model.addAttribute("product26", product26);
        model.addAttribute("product27", product27);
        model.addAttribute("product28", product28);
        model.addAttribute("product29", product29);
        model.addAttribute("product30", product30);
        model.addAttribute("product31", product31);
        model.addAttribute("product32", product32);


        model.addAttribute("showProducts", allProducts);
        return "products/products";
    }

    @GetMapping("/products/t-shirts")

    public String viewTshirts(Model model) {
        if (model.getAttribute("category") != null || model.getAttribute("category") != "") {
            String category = "";
            model.addAttribute("category", category);
        }
        List<Product> allProducts = productRepo.findAll();
        List<Product> chosenProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getType().equals("Shirt")) {
                chosenProducts.add(product);
            }
        }
        model.addAttribute("showProducts", chosenProducts);
        return "products/products";
    }


    @GetMapping("/products/hoodies")

    public String viewPullover(Model model) {
        if (model.getAttribute("category") != null || model.getAttribute("category") != "") {
            String category = "";
            model.addAttribute("category", category);
        }
        List<Product> allProducts = productRepo.findAll();
        List<Product> chosenProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getType().equals("Hoodie")) {
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
        List<Product> allProducts = productRepo.findAll();
        List<Product> chosenProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getType().equals("Hat")) {
                chosenProducts.add(product);
            }
        }
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
        double salePrice = 0;
        Product aProduct = productRepo.getOne(id);
        model.addAttribute("product", aProduct);
        if (aProduct.getSpecial() != null && aProduct.getSpecial()) {
            salePrice = aProduct.getPrice() - (aProduct.getPrice() * 0.42);
            salePrice = Math.round(salePrice * 100.00) / 100.00;
        }
        model.addAttribute("salePrice", salePrice);
        List<Categories> categories = aProduct.getCategories();
        String cNames = "";
        for (Categories category : categories) {
            cNames = cNames + category.getCategory() + ", ";
        }
        cNames = StringUtils.chop(cNames);
        cNames = StringUtils.chop(cNames);
        model.addAttribute("categories", cNames);
        return "products/product";
    }

    @PostMapping("/products/search")
    public String searchProduct(@RequestParam(name = "keyword") String keyword,
                                Model model,
                                @ModelAttribute("category") String category,
                                RedirectAttributes redirectAttributes) {
        List<Product> chosenProducts = new ArrayList<>();
        if (category.equals("")) {
            chosenProducts = productRepo.findByNameContaining(keyword);
        } else {
            chosenProducts = productRepo.findByCategoriesContainingaAndNameContaining(category, keyword);
        }
        redirectAttributes.addFlashAttribute("showProducts", chosenProducts);
        return "redirect:search";
    }

    @GetMapping("/products/search")
    public String searchProductLander(Model model,
                                      @ModelAttribute("showProducts") ArrayList<Product> showProducts) {
        model.addAttribute("showProducts", showProducts);
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
