package com.capstone.ecommerce.repositories;

import com.capstone.ecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    void save(List<Address> addresses);
}
