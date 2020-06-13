package com.capstone.ecommerce.repositories;


import com.capstone.ecommerce.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Transaction, Long> {
    void save(List<Transaction> transactions);
    Transaction findByStripeTransID(String id);
}
