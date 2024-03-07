package com.example.spring.data.with.redis.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // when you want to excute this 
@Target(ElementType.METHOD) // 
public @interface LogExecutionTime {
    
}
