package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    void save(List<ShoppingCart> shoppingCarts);
}
