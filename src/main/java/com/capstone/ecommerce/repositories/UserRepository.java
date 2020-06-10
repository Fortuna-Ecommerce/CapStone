package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

//Represents the "DAO" and how to access the information on the object
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findById(long id);

<<<<<<< HEAD
    User findByUsername(String username);

    //Represents the "DAO" and how to access the information on the object
//    interface UserRepository extends JpaRepository<User, Long> {
//        User findByUsername(String username);
//
//        void save(List<User> users);
//
//    }
}
=======

        User findByUsername(String username);

        void save(List<User> users);

    }

>>>>>>> 08dc9f47bcb96a220e74e7742e44084155934d65

//    @Query("SELECT p FROM User p WHERE p.id LIKE %?1%")
//        List<Product> findByNameContaining(String keyword);
