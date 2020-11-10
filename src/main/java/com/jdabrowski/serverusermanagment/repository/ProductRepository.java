package com.jdabrowski.serverusermanagment.repository;

import com.jdabrowski.serverusermanagment.model.Product;
import com.jdabrowski.serverusermanagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

}
