# Project by Using SpringBoot And Redis Concepts
<br>

## Overview
The main objective of this project is to optimize performance by integrating Redis caching into this project.This is achieved through the creation of a custom annotation called @LogExecutionTime, which is designed to work seamlessly with Spring AOP. By annotating methods with @LogExecutionTime, we can accurately measure the execution time of these methods both before and after utilizing Redis cache to fetch data from the database. This provides valuable insights into the efficiency gains achieved through caching, allowing us to assess the impact of caching on method execution times. 
 <br>
 
## Usages
-  SpringBoot
-  SpringAop
-  Redis
-  MySQL

## Architecture of the Project

 ### 1- src folders
   - Controller
   - Model 
   - Aspect
   - Service
   - Repository
 
 ### 2-Maven pom.xml
<br>

```
 <dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
</dependencies>
 ```
<br>

### 3-Application.properties.yml

```
# Redis Config
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379

# Database Config
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3312/db
spring.datasource.username=db
spring.datasource.password=nFLhPPKOnkW1FA1e
spring.jpa.show-sql=true

 ```


