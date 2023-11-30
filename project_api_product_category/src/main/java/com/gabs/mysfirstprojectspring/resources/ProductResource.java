package com.gabs.mysfirstprojectspring.resources;

import com.gabs.mysfirstprojectspring.entities.Category;
import com.gabs.mysfirstprojectspring.entities.Product;
import com.gabs.mysfirstprojectspring.repositories.CategoryRepository;
import com.gabs.mysfirstprojectspring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //indica que será um controlador rest
@RequestMapping(value = "/products") //endpoint
public class ProductResource {

    @Autowired //indica que o objeto é uma injeção de dependências
    private ProductRepository productRepository;

    @GetMapping //como não informado um valor, indica que é o mapping principal do Request.
    public ResponseEntity<List<Product>> findAll(){
        List<Product> listAllProducts = productRepository.findAll();

        return ResponseEntity.ok().body(listAllProducts);
    }

    @GetMapping(value = "/{id}") //um sub end-point, indica que após o /products/id para buscar por id
    public ResponseEntity<Product> findById(@PathVariable long id) {
        return ResponseEntity.ok().body(productRepository.findById(id).get());
    }
}
