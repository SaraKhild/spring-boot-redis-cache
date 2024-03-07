package com.example.spring.data.with.redis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.example.spring.data.with.redis.aspect.LogExecutionTime;
import com.example.spring.data.with.redis.model.Product;
import com.example.spring.data.with.redis.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @LogExecutionTime
    public List<Product> getAll() {
        return repository.findAll();
    }

    @LogExecutionTime
    @Cacheable(key = "#id", value = "product")
    public Product getById(long id) {
        return repository.findById(id).get();
    }

    @CachePut(key = "#result.id", value = "product")
    public Product save(Product model) {
      return repository.save(model);
      
    }

    // @CachePut(key = "#id", cacheNames = "product")
    @Caching(evict = { @CacheEvict(value = "evictProduct", allEntries = true) }, put = {
            @CachePut(key = "#id", value = "product") })
    public Product update(Product model, long id) {

        var product = repository.findById(id).get();

        product.setName(model.getName());
        product.setCode(model.getCode());
        product.setPrice(model.getPrice());
        product.setQuantity(model.getQuantity());
        repository.save(model);

        return product;
    }

    @CacheEvict(key = "#id", cacheNames = "product")
    public void deleteById(long id) {
        repository.deleteById(id);

    }
}
