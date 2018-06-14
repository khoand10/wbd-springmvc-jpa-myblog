package com.codegym.myblog.controller;

import com.codegym.myblog.model.Category;
import com.codegym.myblog.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@SpringJUnitJupiterConfig(CategoryControllerTestConfig.class)
@WebAppConfiguration
@ContextConfiguration(classes = {CategoryControllerTestConfig.class})
public class CategoryControllerTest {
    
    private static String name;
    private Category category;
    
    static{
        
    }
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    CategoryController categoryController;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver;

    private MockMvc mockMvc;
    private Page<Category> categories;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setCustomArgumentResolvers(pageableHandlerMethodArgumentResolver)
                .build();
    }

    @AfterEach
    void resetMoc() {
        Mockito.reset(categoryService);
    }
    
    @Test
    void categoryList() throws Exception{
        when(categoryService.findAll()).thenReturn(categories);
    }
    
}
