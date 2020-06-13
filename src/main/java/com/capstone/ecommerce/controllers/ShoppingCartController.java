package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.ShoppingCart;
import com.capstone.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("products")
public class ShoppingCartController {

    private ProductRepository productRepo;

    public ShoppingCartController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }


    public List<Product> comparison(List<Product> purchasableProducts) {
        List<Product> comparing = new ArrayList<>();

        for (Product product : purchasableProducts) {
            comparing.add(this.productRepo.getOne(product.getId()));
        }
        return comparing;
    }


    @GetMapping("/cart")
    public String getCart(Model model,
                          @ModelAttribute("products") ShoppingCart products,
                          @ModelAttribute("error") String error) {
//        NumberFormatter formatter = new NumberFormatter(0.00);
        List<Double> originalPrices = new ArrayList<>();
        List<String> colors = new ArrayList<>();
        double total = 0.00;
        if (products != null) {
            for (Product product : products) {
                double originalPrice = 0.00;
                originalPrice = (this.productRepo.findById(product.getId())).getPrice();
                originalPrices.add(originalPrice);
                String color = "";
                if(product.getColor().equals("FFFFFF")){
                    color = "White";
                } else if (product.getColor().equals("000000")){
                    color = "Black";
                } else if (product.getColor().equals("808080")){
                    color = "Grey";
                }
                colors.add(color);
                total = total + product.getPrice();
            }
            total = Math.round(total * 100.00) / 100.00;
        }
        double tempGrandTotal = (total * 0.0825);
        double grandTotal = tempGrandTotal + total + 5.00;
        grandTotal = Math.round(grandTotal * 100.00) / 100.00;
        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("total", total);
        model.addAttribute("error", error);
        model.addAttribute("prices", originalPrices);
        model.addAttribute("colors", colors);
        return "purchases/cart";
    }

    @PostMapping("/addToCart")
    public String addToCart(
            Model model,
            @ModelAttribute("products") ShoppingCart products,
            @RequestParam("productId") long id,
            @RequestParam(value = "sizeSelect", required = false) String size,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam("productName") String name,
            @RequestParam("cartAddQuantity") Integer quantity,
            @RequestParam("price") String price,
            @RequestParam("type") String type,
            RedirectAttributes redir) {
        String error1 = "";
        String error2 = "";
        String error3 = "";
        if(quantity == 0){
            error1 = "Sorry, you can't buy 0 of anything!";
            redir.addFlashAttribute("error1", error1);
            return "redirect:/products/"+id;
        }

        double setPrice = Double.parseDouble(price);
        Product currentProduct = new Product();
        Product newProduct = new Product();

            currentProduct = this.productRepo.findByNameAndSizeAndColorAndType(name, size, color, type);



        double total = 0.00;
        boolean found = false;
        Product addProduct = currentProduct;


        if (quantity != null) {
            addProduct.setQuantity(quantity);
            addProduct.setPrice(quantity * setPrice);
        }
        if (products.size() > 0) {
            for (Product product : products) {
                if (product.getId() == addProduct.getId()) {
                    product.setQuantity(quantity + product.getQuantity());
                    double settingPrice = Math.round((setPrice * product.getQuantity()) * 100) / 100;
                    product.setPrice(settingPrice);
                    product.setName(product.getName());
                    found = true;
                    break;
                }
            }
            if (!found) {
                products.add(addProduct);
            }
        } else {
            products.add(addProduct);
        }
        found = false;

        for (Product product : products) {
            total = total + product.getPrice();
        }

        double tempGrandTotal = (total * 0.0825);
        double grandTotal = tempGrandTotal + total + 5.00;
        grandTotal = Math.round(grandTotal * 100.00) / 100.00;

        List<Product> originals = comparison(products);


        total = Math.round(total * 100.00) / 100.00;
        redir.addFlashAttribute("total", total);
        model.addAttribute("products", products);
        model.addAttribute("originals", originals);
        redir.addFlashAttribute("grandTotal", grandTotal);

        return "redirect:cart";
    }


    @GetMapping("/deleteFromCart")
    public String deleteFromCart(Model model,
                                 @ModelAttribute("products") ShoppingCart products,
                                 @RequestParam("cartDeleteId") Integer id) {
        double total = 0.00;
        Product deleteProduct = productRepo.getOne((long) id);
        if (products.size() >= 0) {
            for (int i = 0; i <= products.size(); i++) {
                if (products.get(i).getId() == deleteProduct.getId()) {
                    products.remove(i);
                    break;
                }
            }
        }

        for (Product product : products) {
            total = total + product.getPrice();
        }
        total = Math.round(total * 100.00) / 100.00;
        model.addAttribute("total", total);
        model.addAttribute("products", products);
        return "redirect:cart";
    }


    @PostMapping("/updateCart")
    public String updateCart(Model model,
                             @ModelAttribute("products") ShoppingCart products) {

        double total = 0.00;
        ShoppingCart updatedCart = new ShoppingCart();
        boolean found = false;
        if (products.size() > 0) {

        }
            for (Product product : products) {
                total = total + product.getPrice();
            }
            total = Math.round(total * 100.00) / 100.00;
            model.addAttribute("total", total);
            model.addAttribute("products", products);
            return "redirect:cart";
        }



    }



