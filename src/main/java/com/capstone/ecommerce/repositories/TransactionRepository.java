package com.capstone.ecommerce.repositories;


import com.capstone.ecommerce.model.Transaction;
import com.capstone.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    void save(List<Transaction> transactions);
    Transaction findByStripeTransID(String id);
    List<Transaction> findByUser (User user);
}
