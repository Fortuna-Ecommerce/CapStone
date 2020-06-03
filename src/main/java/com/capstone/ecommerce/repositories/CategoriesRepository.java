//package com.capstone.ecommerce.repositories;
//
//import com.capstone.ecommerce.model.Categories;
//import com.capstone.ecommerce.model.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface CategoriesRepository extends JpaRepository<Categories, Long> {
//
//    @Query(value = "SELECT category FROM categories JOIN product_categories on categories.id = product_categories.category_id JOIN products AS p2 on product_categories.product_id = p2.id WHERE p2.id = :id",
//            nativeQuery = true)
//    List<Categories> findByProductId(@Param("id") long id);
//}
