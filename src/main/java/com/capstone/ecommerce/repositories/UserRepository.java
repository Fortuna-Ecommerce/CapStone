package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

//Represents the "DAO" and how to access the information on the object
public interface UserRepository extends JpaRepository<User, Long> {


        User findById(long id);

        User findByEmail(String email);

        User findByUsername(String username);

        void save(List<User> users);

    }


//    @Query("SELECT p FROM User p WHERE p.id LIKE %?1%")
//        List<Product> findByNameContaining(String keyword);
