package com.codegym.myblog.repository;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
public class MyblogRepositoryTestConfig {

    @Bean
    public BlogRepository myblogRepository(){
        return new BlogRepository();
    }

    @Bean
    public EntityManager entityManager(){
        return Mockito.mock(EntityManager.class);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
        return entityManagerFactory;
    }

}
