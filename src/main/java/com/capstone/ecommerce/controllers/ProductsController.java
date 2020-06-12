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

        List<Product> showProducts = productRepo.findbySizeandColor("MD", "000000");
        List<Product> hatProducts = productRepo.findbySizeandColor("OSFM", "000000");
        showProducts.addAll(hatProducts);
//
        List<Product> allProducts = productRepo.findAll();
        Product product3 = productRepo.getOne(3L);
        Product product4 = productRepo.getOne(16L);
        Product product5 = productRepo.getOne(32L);
        Product product6 = productRepo.getOne(48L);
        Product product7 = productRepo.getOne(61L);
        Product product8 = productRepo.getOne(76L);
        Product product9 = productRepo.getOne(95L);
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
        Product product33 = productRepo.getOne(33L);
        Product product34 = productRepo.getOne(34L);
        Product product35 = productRepo.getOne(35L);
        Product product36 = productRepo.getOne(36L);
        Product product37 = productRepo.getOne(37L);
        Product product38 = productRepo.getOne(38L);
        Product product39 = productRepo.getOne(39L);
        Product product40 = productRepo.getOne(40L);
        Product product41 = productRepo.getOne(41L);
        Product product42 = productRepo.getOne(42L);
        Product product43 = productRepo.getOne(43L);
        Product product44 = productRepo.getOne(44L);
        Product product45 = productRepo.getOne(45L);
        Product product46 = productRepo.getOne(46L);
        Product product47 = productRepo.getOne(47L);
        Product product48 = productRepo.getOne(48L);
        Product product49 = productRepo.getOne(49L);
        Product product50 = productRepo.getOne(50L);
        Product product51 = productRepo.getOne(51L);
        Product product52 = productRepo.getOne(52L);
        Product product53 = productRepo.getOne(53L);
        Product product54 = productRepo.getOne(54L);
        Product product55 = productRepo.getOne(55L);
        Product product56 = productRepo.getOne(56L);
        Product product57 = productRepo.getOne(57L);
        Product product58 = productRepo.getOne(58L);
        Product product59 = productRepo.getOne(59L);
        Product product60 = productRepo.getOne(60L);
        Product product61 = productRepo.getOne(61L);
        Product product62 = productRepo.getOne(62L);
        Product product63 = productRepo.getOne(63L);
        Product product64 = productRepo.getOne(64L);
        Product product65 = productRepo.getOne(65L);
        Product product66 = productRepo.getOne(66L);
        Product product67 = productRepo.getOne(67L);
        Product product68 = productRepo.getOne(68L);
        Product product69 = productRepo.getOne(69L);
        Product product70 = productRepo.getOne(70L);
        Product product71 = productRepo.getOne(71L);
        Product product72 = productRepo.getOne(72L);
        Product product73 = productRepo.getOne(73L);
        Product product74 = productRepo.getOne(74L);
        Product product75 = productRepo.getOne(75L);
        Product product76 = productRepo.getOne(76L);
        Product product77 = productRepo.getOne(77L);
        Product product78 = productRepo.getOne(78L);
        Product product79 = productRepo.getOne(79L);
        Product product80 = productRepo.getOne(80L);
        Product product81 = productRepo.getOne(81L);
        Product product82 = productRepo.getOne(82L);
        Product product83 = productRepo.getOne(83L);
        Product product84 = productRepo.getOne(84L);
        Product product85 = productRepo.getOne(85L);
        Product product86 = productRepo.getOne(86L);
        Product product87 = productRepo.getOne(87L);
        Product product88 = productRepo.getOne(88L);
        Product product89 = productRepo.getOne(89L);
        Product product90 = productRepo.getOne(90L);
        Product product91 = productRepo.getOne(91L);
        Product product92 = productRepo.getOne(92L);
        Product product93 = productRepo.getOne(93L);
        Product product94 = productRepo.getOne(94L);
        Product product95 = productRepo.getOne(95L);
        Product product96 = productRepo.getOne(96L);
        Product product97 = productRepo.getOne(97L);
        Product product98 = productRepo.getOne(98L);
        Product product99 = productRepo.getOne(99L);
        Product product100 = productRepo.getOne(100L);
        Product product101 = productRepo.getOne(101L);
        Product product102 = productRepo.getOne(102L);
        Product product103 = productRepo.getOne(103L);
        Product product104 = productRepo.getOne(104L);
        Product product105 = productRepo.getOne(105L);
        Product product106 = productRepo.getOne(106L);
        Product product107 = productRepo.getOne(107L);
        Product product108 = productRepo.getOne(108L);
        Product product109 = productRepo.getOne(109L);
        Product product110 = productRepo.getOne(110L);
        Product product111 = productRepo.getOne(111L);
        Product product112 = productRepo.getOne(112L);
        Product product113 = productRepo.getOne(113L);
        Product product114 = productRepo.getOne(114L);
        Product product115 = productRepo.getOne(115L);
        Product product116 = productRepo.getOne(116L);
        Product product117 = productRepo.getOne(117L);
        Product product118 = productRepo.getOne(118L);
        Product product119 = productRepo.getOne(119L);
        Product product120 = productRepo.getOne(120L);
        Product product121 = productRepo.getOne(121L);
        Product product122 = productRepo.getOne(122L);
        Product product123 = productRepo.getOne(123L);
        Product product124 = productRepo.getOne(124L);
        Product product125 = productRepo.getOne(125L);
        Product product126 = productRepo.getOne(126L);
        Product product127 = productRepo.getOne(127L);
        Product product128 = productRepo.getOne(128L);
        Product product129 = productRepo.getOne(129L);
        Product product130 = productRepo.getOne(130L);
        Product product131 = productRepo.getOne(131L);
        Product product132 = productRepo.getOne(132L);
        Product product133 = productRepo.getOne(133L);
        Product product134 = productRepo.getOne(134L);
        Product product135 = productRepo.getOne(135L);
        Product product136 = productRepo.getOne(136L);
        Product product137 = productRepo.getOne(137L);
        Product product138 = productRepo.getOne(138L);
        Product product139 = productRepo.getOne(139L);
        Product product140 = productRepo.getOne(140L);
        Product product141 = productRepo.getOne(141L);
        Product product142 = productRepo.getOne(142L);
        Product product143 = productRepo.getOne(143L);
        Product product144 = productRepo.getOne(144L);
        Product product145 = productRepo.getOne(145L);
        Product product146 = productRepo.getOne(146L);
        Product product147 = productRepo.getOne(147L);
        Product product148 = productRepo.getOne(148L);
        Product product149 = productRepo.getOne(149L);
        Product produc150 = productRepo.getOne(150L);
        Product produc151 = productRepo.getOne(151L);
        Product produc152 = productRepo.getOne(152L);


//        model.addAttribute("product1", product1);
//        model.addAttribute("product2", product2);
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
        model.addAttribute("product33", product33);
        model.addAttribute("product34", product34);
        model.addAttribute("product35", product35);
        model.addAttribute("product36", product36);
        model.addAttribute("product37", product37);
        model.addAttribute("product38", product38);
        model.addAttribute("product39", product39);
        model.addAttribute("product40", product40);
        model.addAttribute("product41", product41);
        model.addAttribute("product42", product42);
        model.addAttribute("product43", product43);
        model.addAttribute("product44", product44);
        model.addAttribute("product45", product45);
        model.addAttribute("product46", product46);
        model.addAttribute("product47", product47);
        model.addAttribute("product48", product48);
        model.addAttribute("product49", product49);
        model.addAttribute("product50", product50);
        model.addAttribute("product51", product51);
        model.addAttribute("product52", product52);
        model.addAttribute("product53", product53);
        model.addAttribute("product54", product54);
        model.addAttribute("product55", product55);
        model.addAttribute("product56", product56);
        model.addAttribute("product57", product57);
        model.addAttribute("product58", product58);
        model.addAttribute("product59", product59);
        model.addAttribute("product60", product60);
        model.addAttribute("product61", product61);
        model.addAttribute("product62", product62);
        model.addAttribute("product63", product63);
        model.addAttribute("product64", product64);
        model.addAttribute("product65", product65);
        model.addAttribute("product66", product66);
        model.addAttribute("product67", product67);
        model.addAttribute("product68", product68);
        model.addAttribute("product69", product69);
        model.addAttribute("product70", product70);
        model.addAttribute("product71", product71);
        model.addAttribute("product72", product72);
        model.addAttribute("product73", product73);
        model.addAttribute("product74", product74);
        model.addAttribute("product75", product75);
        model.addAttribute("product76", product76);
        model.addAttribute("product77", product77);
        model.addAttribute("product78", product78);
        model.addAttribute("product79", product79);
        model.addAttribute("product80", product80);
        model.addAttribute("product81", product81);
        model.addAttribute("product82", product82);
        model.addAttribute("product83", product83);
        model.addAttribute("product84", product84);
        model.addAttribute("product85", product85);
        model.addAttribute("product86", product86);
        model.addAttribute("product87", product87);
        model.addAttribute("product88", product88);
        model.addAttribute("product89", product89);
        model.addAttribute("product90", product90);
        model.addAttribute("product91", product91);
        model.addAttribute("product92", product92);
        model.addAttribute("product93", product93);
        model.addAttribute("product94", product94);
        model.addAttribute("product95", product95);
        model.addAttribute("product96", product96);
        model.addAttribute("product97", product97);
        model.addAttribute("product98", product98);
        model.addAttribute("product99", product99);
        model.addAttribute("product100", product100);
        model.addAttribute("product101", product101);
        model.addAttribute("product102", product102);
        model.addAttribute("product103", product103);
        model.addAttribute("product104", product104);
        model.addAttribute("product105", product105);
        model.addAttribute("product106", product106);
        model.addAttribute("product107", product107);
        model.addAttribute("product108", product108);
        model.addAttribute("product109", product109);
        model.addAttribute("product110", product110);
        model.addAttribute("product111", product111);
        model.addAttribute("product112", product112);
        model.addAttribute("product113", product113);
        model.addAttribute("product114", product114);
        model.addAttribute("product115", product115);
        model.addAttribute("product116", product116);
        model.addAttribute("product117", product117);
        model.addAttribute("product118", product118);
        model.addAttribute("product119", product119);
        model.addAttribute("product120", product120);
        model.addAttribute("product121", product121);
        model.addAttribute("product122", product122);



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
        List<Product> chosenProducts = productRepo.findbySizeandColor("XS", "000000");
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
        List<Product> allProducts = productRepo.findbySizeandColor("MD", "000000");
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
