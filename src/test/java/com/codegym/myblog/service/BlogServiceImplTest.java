package com.codegym.myblog.service;

import static org.junit.jupiter.api.Assertions.*;

import com.codegym.myblog.model.Blog;
import com.codegym.myblog.model.Category;
import com.codegym.myblog.repository.BlogRepository;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringJUnitJupiterConfig(BlogServiceTestConfig.class)
class BlogServiceImplTest {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogRepository blogRepository;

    // data
    private static final int BLOG_ID = 1;

    private static Blog BLOG_NULL = new Blog();

    private static String TITLE = "NodeJs";
    private static String SUMMARU = "NodeJs 2018";
    private static String CONTENT = "NodeJs is JavaScript";
    private static LocalDate DATE = LocalDate.now();
    private static Category CATEGORY = new Category("Programmer");
    private static Blog BLOG = new Blog(TITLE, SUMMARU, CONTENT, CATEGORY);

    private static List<Blog> blogs = Arrays.asList(BLOG);
    static private Page<Blog> BLOGSPAGE = new PageImpl<>(blogs);
    static private Pageable PAGEABLE = new PageRequest(0,10);

    @Test
    void getAll(){
        when(blogRepository.findAll(PAGEABLE)).thenReturn(BLOGSPAGE);
        assertEquals(BLOGSPAGE, blogService.findAll(PAGEABLE));
        verify(blogRepository).findAll(PAGEABLE);
    }

    @AfterEach
    private void resetMocks(){
        Mockito.reset(blogRepository);
    }

    @Test
    void findById(){
        when(blogRepository.findOne(BLOG_ID)).thenReturn(BLOG);
        Blog resultBlog = blogService.findById(BLOG_ID);
        assertEquals(BLOG, resultBlog);
        verify(blogRepository).findOne(BLOG_ID);
    }

    @Test
    void findByIdNull(){
        when(blogRepository.findOne(BLOG_ID)).thenReturn(BLOG_NULL);
        Blog resultBlog = blogService.findById(BLOG_ID);
        assertEquals(BLOG_NULL, resultBlog);
        verify(blogRepository).findOne(BLOG_ID);
    }

    @Test
    void save(){
        when(blogRepository.save(BLOG)).thenReturn(BLOG);
        Blog resoutBlog = blogService.save(BLOG);
        assertEquals(BLOG, resoutBlog);
        verify(blogRepository).save(BLOG);
    }

}
