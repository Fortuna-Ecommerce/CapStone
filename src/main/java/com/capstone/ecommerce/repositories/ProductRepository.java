package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  void save(List<Product> products);
    List<Product> findAll();

}
