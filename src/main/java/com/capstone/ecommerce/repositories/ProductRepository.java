package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

  void save(List<Product> products);
//  Product getOne(long id);

@Transactional
  Product findById(long id);

//  List<Product> findAll(String keyword);

//  Product getProductById(long id);


  //SEARCH METHOD
//  @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
  List<Product> findByNameContaining(String keyword);

  @Query(value = "SELECT * FROM products JOIN product_categories on products.id = product_categories.product_id JOIN " +
          "categories c2 on product_categories.category_id = c2.id WHERE c2.category LIKE %:keyword%",
          nativeQuery = true)
  List<Product> findByCategoriesContaining(@Param("keyword") String keyword);

  @Query(value = "SELECT * FROM products JOIN product_categories on products.id = product_categories.product_id JOIN " +
          "categories c2 on product_categories.category_id = c2.id WHERE c2.category LIKE %:keyword% AND products" +
          ".name LIKE %:pname%",
          nativeQuery = true)
  List<Product> findByCategoriesContainingaAndNameContaining(@Param("keyword") String keyword,
                                                             @Param("pname") String pname);

    @Query(value = "SELECT * FROM products WHERE size = :size AND color = :color",
          nativeQuery = true)
    List<Product> findbySizeandColor(@Param("size") String size,
                                                       @Param("color") String color);

  Product findByNameAndSizeAndColor(String name, String size, String color);
  Product findByNameAndSizeAndColorAndType(String name, String size, String color, String type);

  Product findByNameAndColor(String name, String color);
@Query(value = "SELECT * FROM products WHERE size = :size AND color = :color AND name LIKE %:keyword%", nativeQuery =
        true)
  List<Product> findBySizeAndColorAndNameContaining(String size, String color, String keyword);

 List<Product> findBySize(String size);

 List<Product> findBySizeAndNameContaining(String size, String keyword);

}


//  Product findByTitle(String title);

//SEARCH METHOD
//  @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
//  List<Product> findByNameContaining(String keyword);


