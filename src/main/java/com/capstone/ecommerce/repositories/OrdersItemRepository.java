package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.Order;
import com.capstone.ecommerce.model.OrdersItem;
import com.capstone.ecommerce.model.Product;
import com.capstone.ecommerce.model.Transactions_Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersItemRepository extends JpaRepository<Transactions_Product, Long> {
    void save(OrdersItem OrdersItem);
    void deleteByProduct(Product product);

//    @Modifying
//    @Query(value = "DELETE FROM transactions_product WHERE product_id = :id",nativeQuery = true)
//    void deleteByProductId(@Param("id") long id);

//    void save(Transactions_Product transactions_product);
}
