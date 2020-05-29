package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//  void save(List<Product> products);

//  List<Product> findAll(String keyword);

  @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
  List<Product> findByNameContaining(String keyword);



//  @Query("SELECT path FROM product_images")
//  ProductImages getFilePath(String path);

}
