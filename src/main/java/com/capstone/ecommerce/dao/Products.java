package com.capstone.ecommerce.dao;

import com.capstone.ecommerce.model.Product;

import java.sql.ResultSet;
import java.util.List;

public interface Products {
    // get a list of all the products
    List<Product> all();

    // get a single product by id
    Product getById(long id);

    // insert a new product and return the new products's id
    Long insert(Product product);



    int updateProduct(Product update);

    void deleteProduct(int delproductID);

    Object findById(long id);

    //Take a single ad and post information
    List<Products> getByTitle(String title);

    List<Product> createProductsFromResults(ResultSet rs);
}