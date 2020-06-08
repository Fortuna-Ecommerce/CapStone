package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.Transactions_Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionProductRepository extends JpaRepository<Transactions_Product, Long> {

    void deleteByProduct(Product product);

//    @Modifying
//    @Query(value = "DELETE FROM transactions_product WHERE product_id = :id",nativeQuery = true)
//    void deleteByProductId(@Param("id") long id);

//    void save(Transactions_Product transactions_product);
}
