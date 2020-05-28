package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById();
=======


import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void save(List<User> users);
>>>>>>> 53f0a24b56f8b6feebf648a8fdb55658839f9ab4
}
