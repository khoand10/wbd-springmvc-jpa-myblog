package com.codegym.myblog.controller;

import com.codegym.myblog.model.Blog;
import com.codegym.myblog.model.BlogForm;
import com.codegym.myblog.model.Category;
import com.codegym.myblog.service.BlogService;
import com.codegym.myblog.service.CategoryService;
import com.codegym.myblog.until.StorageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;
import java.io.File;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/blog")
    public ModelAndView loadIndex(Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView loadCreate() {
        ModelAndView modelAndView = new ModelAndView("/blog/create", "blogF", new BlogForm());
        return modelAndView;
    }

    @PostMapping("/post")
    public ModelAndView save(@ModelAttribute("blogF")BlogForm blogFrom, ModelMap model){
        try {
            String randomCode = UUID.randomUUID().toString();
            String originFileName = blogFrom.getFeature().getOriginalFilename();
            String randomName = randomCode + StorageUtils.getFileExtension(originFileName);
            blogFrom.getFeature().transferTo(new File(StorageUtils.FEATURE_LOCATION + "/" + randomName));
            Blog post = new Blog();

            post.setTitle(blogFrom.getTitle());
            post.setSummary(blogFrom.getSummary());
            post.setContent(blogFrom.getContent());
            post.setDate(LocalDate.now());
            post.setImage(randomName);
            post.setCategory(blogFrom.getCategory());

            blogService.save(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blogF", new BlogForm());
        modelAndView.addObject("message", "Create saved successfully");
        return modelAndView;
    }

    @GetMapping("/read/{id}")
    public ModelAndView readBlog(@PathVariable("id")int id) {
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        modelAndView.addObject("blog",blogService.findById(id));
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")int id){
        Blog blog = blogService.findById(id);
        BlogForm postForm = new BlogForm();
        postForm.setId((long)blog.getId());
        postForm.setTitle(blog.getTitle());
        postForm.setSummary(blog.getSummary());
        postForm.setContent(blog.getContent());
        postForm.setCategory(blog.getCategory());
        postForm.setFeatureUrl(blog.getImage());
        System.out.println(postForm.getCategory().getId());
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blogF",postForm);
        modelAndView.addObject("message", "Update saved successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        //blogService.remove(id);
        return "redirect:/blog";
    }

}
