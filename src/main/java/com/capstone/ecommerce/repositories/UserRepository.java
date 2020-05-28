package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void save(List<User> users);
}
