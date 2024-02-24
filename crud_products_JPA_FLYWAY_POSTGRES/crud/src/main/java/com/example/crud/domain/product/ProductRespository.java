package com.example.crud.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRespository extends JpaRepository<Product, String> {

    List<Product> findAllByActiveTrue();
    List<Product> findAllByActiveFalse();

    List<Product> findAllByActiveTrueOrderByName();
}
