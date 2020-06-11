package com.capstone.ecommerce.controllers;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.User;
import com.capstone.ecommerce.repositories.ProductRepository;
import com.capstone.ecommerce.repositories.TransactionProductRepository;
import com.capstone.ecommerce.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping(value = "/productapi")
public class ProductAPIController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TransactionProductRepository transProdRepo;

    private final Logger errorLogger= LoggerFactory.getLogger(ProductAPIController.class);


    @GetMapping(value = {"/getproducts","/getproduct/{id}"})
    public List<Product> getProducts(@PathVariable(name="id", required = false)Long productId) {

        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepo.getOne(person.getId());
        List<Product> productsFound=new ArrayList<Product>();
        if(user.getAdmin()){

            if(productId==null) {
                productsFound.addAll(productRepo.findAll());
            }
            else{
                productsFound.add(productRepo.findById(productId.longValue()));
            }
        }
        return productsFound;
    }

    @PostMapping(value = {"/productsaveedit"}, consumes = "application/json", produces = "application/json")
    public Product saveProduct(@RequestBody Product modifiedProduct) {
        Product targetProduct=null;

        //return nothing if we get nothing
        if(modifiedProduct==null){
            return new Product();
        }

        User person = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepo.getOne(person.getId());

        //try to find the matching product in the repository and return and empty product if it cannot be found
        try{
            targetProduct=productRepo.findById(modifiedProduct.getId());
            if(targetProduct==null){
                return new Product();
            }
        }
        catch(Exception e){
            errorLogger.error("Failed to query product repository.");
            return new Product();
        }

        //modify and save product if it is found and the user is an admin
        if(user.getAdmin()){
            try {
                targetProduct.setPrice(modifiedProduct.getPrice());
                targetProduct.setDescription(modifiedProduct.getDescription());
                targetProduct.setQuantity(modifiedProduct.getQuantity());
                targetProduct.setSpecial(modifiedProduct.getSpecial());
                productRepo.save(targetProduct);
            }
            catch(Exception e){
                errorLogger.error("Failed to setup and save target product");
                return new Product();
            }

        }
        return targetProduct;
    }

}
