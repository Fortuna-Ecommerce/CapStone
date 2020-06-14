package com.capstone.ecommerce.repositories;


import com.capstone.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    void save(List<Order> Orders);
    List<Order> findByUserName(String userName);
}
