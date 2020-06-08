package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.Transactions_Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionProductRepository extends JpaRepository<Transactions_Product, Long> {

//    void save(Transactions_Product transactions_product);
}
