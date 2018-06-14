package com.codegym.myblog.controller;

import com.codegym.myblog.model.Blog;
import com.codegym.myblog.model.Category;
import com.codegym.myblog.service.BlogService;
import com.codegym.myblog.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

@SpringJUnitJupiterConfig(BlogControllerTestConfig.class)
@WebAppConfiguration
@ContextConfiguration(classes = {BlogControllerTestConfig.class})
class BlogControllerTest {

    private static final String URL_BLOG_LIST = "/blog";
    private static final String VIEW_BLOG_LIST = "/blog/index";
    private static final String URL_BLOG_CREATE = "/create";
    private static final String VIEW_BLOG_CREATE = "/blog/create";
    private static final String URL_BLOG_SAVE = "/post";
    private static final String VIEW_BLOG_DETAIL = "";
    private static final String MESSAGE_SUCCESS = "Save succesfully";
    private static final int ID = 1;
    private static final String URL_BLOG_READ = "/read/1";
    private static final String VIEW_BLOG_READ = "/blog/view";
    private static final String URL_BLOG_EDIT = "/edit/1";
    private static final String URL_BLOG_DELETE = "/delete/1";
    private static final String VIEW_BLOG_REDIRECT_LIST = "redirect:/blog";

    private static Long id;
    private static String title = "Firstname";
    private static String summary = "Lastname";
    private static ArrayList<Blog> blogs;
    private static ArrayList<Blog> blogs1;
    private static Page<Blog> blogPage;
    private static Page<Blog> blogPage1;
    private static Blog blog;
    private static Blog blog1;
    private static Pageable pageable;
    private static Category category;

    private static String content = "conent";

    static {
        id = 1l;
        blog = new Blog(title, summary, content, category);
        blog1 = null;
        blogs1 = new ArrayList<>();
        blogs = new ArrayList<>();

        blogs.add(blog);

        blogPage1 = new PageImpl<>(blogs);
        blogPage = new PageImpl<>(blogs);

        pageable = new PageRequest(0, 20);

        category = new Category("Programmer");
    }

    @Autowired
    private BlogController blogController;

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(blogController)
                .setCustomArgumentResolvers(pageableHandlerMethodArgumentResolver)
                .build();
    }

    @AfterEach
    void resetMoc() {
        Mockito.reset(blogService);
    }

    @Test
    void listBlog() throws Exception {
        when(blogService.findAll(pageable)).thenReturn(blogPage);
        mockMvc.perform(get(URL_BLOG_LIST))
                .andExpect(view().name(VIEW_BLOG_LIST))
                .andExpect(model().attribute("blogs", blogPage));
        verify(blogService).findAll(pageable);
    }

    @Test
    void createForm() throws Exception{
        mockMvc.perform(get(URL_BLOG_CREATE))
                .andExpect(view().name(VIEW_BLOG_CREATE));
    }

    @Test
    void createBlog() throws Exception{
        when(blogService.save(blog)).thenReturn(blog);
        mockMvc.perform(post(URL_BLOG_SAVE))
                .andExpect(view().name(VIEW_BLOG_CREATE))
                .andExpect(model().attribute("message",MESSAGE_SUCCESS));
    }

    @Test
    void readBlog()throws Exception{
        when(blogService.findById(ID)).thenReturn(blog);
        mockMvc.perform(get(URL_BLOG_READ))
                .andExpect(view().name(VIEW_BLOG_READ))
                .andExpect(model().attribute("blog", blog));
    }

    @Test
    void editBlog() throws Exception{
        when(blogService.findById(ID)).thenReturn(blog);
        mockMvc.perform(get(URL_BLOG_EDIT))
                .andExpect(view().name(VIEW_BLOG_CREATE))
                .andExpect(model().attribute("blog", blog));
    }

    void saveBlog() throws Exception{
        when(blogService.save(blog)).thenReturn(blog);
        mockMvc.perform(post(URL_BLOG_CREATE))
                .andExpect(view().name(VIEW_BLOG_CREATE))
                .andExpect(model().attribute("message",MESSAGE_SUCCESS));
    }

    @Test
    void deleteBlog() throws Exception{
        mockMvc.perform(get(URL_BLOG_DELETE))
                .andExpect(view().name(VIEW_BLOG_REDIRECT_LIST));
        verify(blogService).remove(ID);
    }

}