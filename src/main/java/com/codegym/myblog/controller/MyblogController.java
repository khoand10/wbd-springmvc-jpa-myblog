package com.codegym.myblog.controller;

import com.codegym.myblog.model.Myblog;
import com.codegym.myblog.service.MyblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/")
public class MyblogController {

    @Autowired
    MyblogService myblogService;

    @GetMapping("")
    public String loadIndex(ModelMap model){
        model.addAttribute("list",myblogService.getAllBlog());
        System.out.println(myblogService.getAllBlog().toString());
        return "index";
    }

    @GetMapping("create")
    public String loadCreateForm(ModelMap model){
        model.addAttribute("myblog",new Myblog());
        return "create";
    }

    @PostMapping("post")
    public String postBlog(Myblog myblog, ModelMap model){
        myblogService.save(myblog);
        model.addAttribute("message","Success!");
        return "create";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id")int id){
        myblogService.remove(id);
        return "index";
    }

    @PostMapping("update")
    public String update(Myblog myblog){
        myblogService.update(myblog);
        return "view";
    }

    @GetMapping("view/{id}")
    public String view(@PathVariable("id")int id){
        return "view";
    }

}
