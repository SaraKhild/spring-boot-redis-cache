package com.example.spring.data.with.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.data.with.redis.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
