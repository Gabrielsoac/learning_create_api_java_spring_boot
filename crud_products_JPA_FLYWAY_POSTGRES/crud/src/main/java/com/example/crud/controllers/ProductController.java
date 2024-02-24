package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRespository;
import com.example.crud.domain.product.RequestProduct;
import com.example.crud.infra.RequestsExceptionHandler;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRespository productRespository;

    @GetMapping
    public ResponseEntity getAllProducts(){
        var allProducts = productRespository.findAllByActiveTrue();

        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/off")
    public ResponseEntity getAllProductsOff(){
        var allProductsOff = productRespository.findAllByActiveFalse();

        return  ResponseEntity.ok(allProductsOff);
    }

    @GetMapping("/orderByName")
    public ResponseEntity getAllProductsOrderByName(){
        var allProductsByName = productRespository.findAllByActiveTrueOrderByName();

        return ResponseEntity.ok(allProductsByName);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data) {
        System.out.println(data);
        Product newProduct = new Product(data);
        productRespository.save(newProduct);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {
        Optional<Product> optionalProduct = productRespository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        Optional<Product> productOptional = productRespository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
        }
        else {
            throw new EntityNotFoundException();
        }
    }



}
