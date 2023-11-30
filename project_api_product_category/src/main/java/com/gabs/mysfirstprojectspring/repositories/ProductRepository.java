package com.gabs.mysfirstprojectspring.repositories;

import com.gabs.mysfirstprojectspring.entities.Category;
import com.gabs.mysfirstprojectspring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}
