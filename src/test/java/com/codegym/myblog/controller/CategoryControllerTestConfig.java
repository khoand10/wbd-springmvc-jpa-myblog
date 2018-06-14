package com.codegym.myblog.controller;

import com.codegym.myblog.service.CategoryService;
import com.codegym.myblog.service.impl.CategoryServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.codegym.myblog")
@EnableSpringDataWebSupport
public class CategoryControllerTestConfig {

    @Bean
    public CategoryService categoryService(){
        return Mockito.mock(CategoryServiceImpl.class);
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
                .setName("myblog")
                .build();
        return db;
    }

}
