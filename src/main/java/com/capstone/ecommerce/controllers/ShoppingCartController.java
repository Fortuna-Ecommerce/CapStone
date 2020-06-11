package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.ShoppingCart;
import com.capstone.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        double total = 0.00;
        if (products != null) {
            for (Product product : products) {
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
        return "purchases/cart";
    }

    @PostMapping("/addToCart")
    public String addToCart(
            Model model,
            @ModelAttribute("products") ShoppingCart products,
            @RequestParam("productId") long id,
            @RequestParam("sizeSelect") String size,
            @RequestParam("color") String color,
            @RequestParam("productName") String name,
            @RequestParam("cartAddQuantity") Integer quantity,
            @RequestParam("price") String price,
            @RequestParam("type") String type,
            RedirectAttributes redir) {
        double setPrice = Double.parseDouble(price);
        String error = "";
        Product currentProduct = new Product();
        Product newProduct = new Product();
//        if(this.productRepo.findByNameAndSizeAndColor(name, size, color) == null){
//            Product tempProduct = new Product();
//           tempProduct.setColor(color);
//            tempProduct.setName(name);
//            tempProduct.setSize(size);
//            tempProduct.setDescription("Brand new meme shirt!");
//            tempProduct.setQuantity(300);
//            tempProduct.setType(type);
//            tempProduct.setSpecial(false);
//            tempProduct.setPrice(setPrice);
//            this.productRepo.save(tempProduct);
//           newProduct = this.productRepo.findByNameAndSizeAndColor(name, size, color);
//           newProduct.setName(name);
//            System.out.println(newProduct.getName());
//            error = "Sorry, out of stock on that!";
//            redir.addFlashAttribute("error", error);
////            model.addAttribute("product", this.productRepo.findById(id));
//            model.addAttribute("products", products);
//            return "redirect:products/"+id;
//        } else {
            currentProduct = this.productRepo.findByNameAndSizeAndColorAndType(name, size, color, type);
//        }

//        if(quantity == null || quantity == 0){
//            error = "Can't order nothing! Please put in a number!";
//            redir.addFlashAttribute("error", error);
//            model.addAttribute("product", currentProduct);
//            model.addAttribute("products", products);
//            return "redirect:products/"+id;
//        }
//
//
//
//        if(currentProduct.getQuantity() < (long)quantity){
//            error = "You can't order that many, sorry! Try a lower number please!";
//            redir.addFlashAttribute("error", error);
//            model.addAttribute("product", currentProduct);
//            model.addAttribute("products", products);
//            return "redirect:products/"+currentProduct.getId();
//        }



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

//
//                for (Product product : products) {
//                    if (product.getId() == addProduct.getId()) {
//                        product.setQuantity(quantity + product.getQuantity());
//                        product.setPrice(product.getPrice() + addProduct.getPrice());
//                        found = true;
//                    }
//                }
//                    updatedCart.add(addProduct);
//                    found=true;
//                } else {
//                    updatedCart.add(product);
//                }
//                if (!found) {
//                    products.add(addProduct);
//                    found = true;
//                }
//            else{
//                products.add(addProduct);
//            }
//            products=updatedCart;

        }
            for (Product product : products) {
                total = total + product.getPrice();
            }
            total = Math.round(total * 100.00) / 100.00;
            model.addAttribute("total", total);
            model.addAttribute("products", products);
            return "redirect:cart";
        }
//    @PostMapping("/checkout")
//    public RedirectView create(
//            @ModelAttribute Product product,
//            @ModelAttribute("products") ShoppingCart products,
//            RedirectAttributes attributes) {
//        products.add(product);
//        attributes.addFlashAttribute("products", products);
//        return new RedirectView("/sessionattributes/todos.html");
//    }


    }



