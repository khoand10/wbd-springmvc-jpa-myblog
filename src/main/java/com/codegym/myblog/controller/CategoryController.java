package com.codegym.myblog.controller;

import com.codegym.myblog.model.Category;
import com.codegym.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public ModelAndView loadIndex(){
        ModelAndView modelAndView = new ModelAndView("/category/index");
        modelAndView.addObject("categories",categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/category/create")
    public ModelAndView getCreate(){
        return new ModelAndView("/category/create","category",new Category());
    }

    @PostMapping("/category/save")
    public String save(Category category){
        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("/category/edit/{id}")
    public ModelAndView getEdit(@PathVariable("id")int id){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        final Category category = categoryService.findById(id);
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @GetMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") int id){
        categoryService.remove(id);
        return "redirect:/category";
    }

}
