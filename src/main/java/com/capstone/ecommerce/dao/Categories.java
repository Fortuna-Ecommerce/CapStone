package com.capstone.ecommerce.dao;

import com.capstone.ecommerce.model.Product;
import jdk.jfr.Category;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface Categories {
    List<Category> all();

    int insert(int id, int categoryId);

    List<Category> combined(Product product);

    int deleteCategoriesPerProduct(int id);

    List<Product> allProductsByCategory(String category);

    ArrayList<String> findByProductId(long id);

    ArrayList<String> createCategoriesFromResults(ResultSet rs);
}