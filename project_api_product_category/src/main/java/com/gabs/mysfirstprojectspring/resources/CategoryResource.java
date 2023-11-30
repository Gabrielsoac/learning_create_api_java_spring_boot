package com.gabs.mysfirstprojectspring.resources;

import com.gabs.mysfirstprojectspring.entities.Category;
import com.gabs.mysfirstprojectspring.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController //indica que será um controlador rest
@RequestMapping(value = "/categories") //endpoint
public class CategoryResource {

    @Autowired //indica que o objeto é uma injeção de dependências
    private CategoryRepository categoryRepository;

    @GetMapping //como não informado um valor, indica que é o mapping principal do Request.
    public ResponseEntity<List<Category>> findAll(){
        List<Category> listAllCategories = categoryRepository.findAll();

        return ResponseEntity.ok().body(listAllCategories);
    }

    @GetMapping(value = "/{id}") //um sub end-point, indica que após o /categories/id para buscar por id
    public ResponseEntity<Category> findById(@PathVariable long id) {
        return ResponseEntity.ok().body(categoryRepository.findById(id).get());
    }
}
