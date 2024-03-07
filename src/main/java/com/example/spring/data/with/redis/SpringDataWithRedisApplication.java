package com.example.spring.data.with.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringDataWithRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataWithRedisApplication.class, args);
	}

}
