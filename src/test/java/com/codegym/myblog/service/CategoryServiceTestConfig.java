package com.codegym.myblog.service;

import com.codegym.myblog.repository.CategoryRepository;
import com.codegym.myblog.service.impl.CategoryServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;

@Configuration
public class CategoryServiceTestConfig {

    @Bean
    public CategoryRepository categoryRepository(){
        return Mockito.mock(CategoryRepository.class);
    }

    @Bean
    public CategoryService categoryService(){
        return new CategoryServiceImpl();
    }

}
