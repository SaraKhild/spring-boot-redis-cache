# Project by Using SpringBoot and Redis Caching Concepts
<br>

## Overview
The main objective of this project is to optimize performance by integrating Redis Caching into this project.This is achieved through the creation of a custom annotation called @LogExecutionTime, which is designed to work seamlessly with Spring AOP. By annotating methods with @LogExecutionTime, we can accurately measure the execution time of these methods both before and after utilizing Redis Cache to fetch data from the database. This provides valuable insights into the efficiency gains achieved through caching, allowing us to assess the impact of caching on method execution times. 
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
## Let's Start :mechanical_arm:
 
##### :pencil2: Create entity called product. Since Redis is an in-memeory database, we need to transform our object into stream of bytes for storing  as well as the other way around for retrieving data. So we nedd to serialize/deserialize by implements the Serializable class.

###### Code :computer:
```
@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String code;
    private int quantity;
    private double price; 
}
```
##### :pencil2: We have repository for basic CRUD operations. 
###### Code :computer:
```
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```
##### :pencil2: @EnableCaching annotation that's will trigger inspects every Spring bean for the presence of caching annotations on methods.
###### Code :computer:
```
@SpringBootApplication
@EnableCaching
public class SpringDataWithRedisApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringDataWithRedisApplication.class, args);
	}
   }
```
##### :pencil2: @Cacheable is used to bring data from database and storing it in the cache. In future, the method retrives data from the cache value directly.The value attribute establishes a cache with a specific name and the key is a unique identifier that represents a specific piece of cached data. Accordingly, the method result is stored in the "product" cache, where respective "id" of product as the unique key.
###### Code :computer: 
```
@Cacheable(key = "#id", value = "product")
public Product getById(long id) {
   return repository.findById(id).get();
  }
```
##### :pencil2: @Caching is used for multiple nested caching on the same method. Inside @CacheEvict the allEntries attribute allow us to remove all given cache by make it as true with another cache name then we going to update data in the cache using @CachePut.
###### Code :computer:
```
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
```
##### :pencil2: @CacheEvict is used for removing data from the cache based on id product identifier.
###### Code :computer:
```
@CacheEvict(key = "#id", cacheNames = "product")
public void deleteById(long id) {
 repository.deleteById(id);
    }
```
##### :pencil2: As you can see the time taken when I invovic the specific product id from database as 1800ms. While, when I invoviced it again but this time it going fetch it from cache as 16ms. If you notic the last line when I invoviced  it once another time  the excutation time will be less as 8ms.
###### Result :star_struck:

<br>

<img width="587" alt="output" src="https://github.com/SaraKhild/spring-boot-redis-cache/assets/67427643/82a67f28-1315-449a-8b21-6059d1fdf030">
  
---
<br>

### Good Luck <img src="https://media.giphy.com/media/hvRJCLFzcasrR4ia7z/giphy.gif" width="30px"> 
