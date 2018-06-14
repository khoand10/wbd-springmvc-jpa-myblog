package com.codegym.myblog.service;


import com.codegym.myblog.repository.BlogRepository;
import com.codegym.myblog.service.impl.BlogServiceImpl;
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
public class BlogServiceTestConfig {

    @Bean
    public BlogService blogService(){
        return new BlogServiceImpl();
    }


    @Bean
    public BlogRepository blogRepository(){
        return Mockito.mock(BlogRepository.class);
    }
}
