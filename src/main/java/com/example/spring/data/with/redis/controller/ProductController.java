package com.example.spring.data.with.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.data.with.redis.aspect.LogExecutionTime;
import com.example.spring.data.with.redis.model.Product;
import com.example.spring.data.with.redis.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService service;

    @LogExecutionTime
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @LogExecutionTime
    @GetMapping("{id}")
    public Product getById(@PathVariable long id) {
        return service.getById(id);
    }

    @LogExecutionTime
    @PostMapping
    public Product save(@RequestBody Product model) {
       return service.save(model);
    }

    @LogExecutionTime
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }

    @LogExecutionTime
    @PutMapping("{id}")
    public Product update(@RequestBody Product model, @PathVariable long id) {
        return service.update(model, id);
    }
}
