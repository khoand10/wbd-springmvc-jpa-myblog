package com.codegym.myblog.controller;

import com.codegym.myblog.model.Blog;
import com.codegym.myblog.model.Category;
import com.codegym.myblog.service.BlogService;
import com.codegym.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

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
        final Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/blog/index");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView loadCreate() {
        ModelAndView modelAndView = new ModelAndView("/blog/create", "blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/post")
    public ModelAndView save(Blog blog){
        blogService.save(blog);
        return new ModelAndView("/blog/create","message","Save succesfully");
    }

    @GetMapping("/read/{id}")
    public ModelAndView readBlog(@PathVariable("id")int id) {
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        modelAndView.addObject("blog",blogService.findById(id));
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")int id){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog",blogService.findById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        blogService.remove(id);
        return "redirect:/blog";
    }

}
