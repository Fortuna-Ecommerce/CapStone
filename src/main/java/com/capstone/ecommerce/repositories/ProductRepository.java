package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findAll();

}
