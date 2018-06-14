package com.codegym.myblog.controller.api;

import com.codegym.myblog.model.Blog;
import com.codegym.myblog.model.Category;
import com.codegym.myblog.service.BlogService;
import com.codegym.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(value = "*")
public class BlogControllerApi {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<Blog>> getAll(Pageable pageable){

        Page<Blog> blogs = blogService.findAll(pageable);

        return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.OK);
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ResponseEntity<Blog> getById(@PathVariable("id") int id){
        Blog blog = blogService.findById(id);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

}
