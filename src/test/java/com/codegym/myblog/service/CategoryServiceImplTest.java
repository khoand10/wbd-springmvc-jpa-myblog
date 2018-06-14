package com.codegym.myblog.service;

import static org.junit.jupiter.api.Assertions.*;

import com.codegym.myblog.model.Blog;
import com.codegym.myblog.model.Category;
import com.codegym.myblog.repository.BlogRepository;

import static org.mockito.Mockito.*;

import com.codegym.myblog.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@SpringJUnitJupiterConfig(CategoryServiceTestConfig.class)
public class CategoryServiceImplTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    static Category category = new Category("Programmer");
    static List<Category> categories = Arrays.asList(category);
    static Iterable<Category> categoryIterable = new PageImpl<>(categories);
    static Iterable<Category> categoryIterableNull = null;

    @AfterEach
    private void resetMocks(){
        Mockito.reset(categoryRepository);
    }

    @Test
    void getAll(){
        when(categoryRepository.findAll()).thenReturn(categoryIterable);
        assertEquals(categoryIterable, categoryRepository.findAll());
        verify(categoryRepository).findAll();
    }

    @Test
    void getAllNull(){
        when(categoryRepository.findAll()).thenReturn(categoryIterableNull);
        assertNull(categoryRepository.findAll());
        verify(categoryRepository).findAll();
    }
}
