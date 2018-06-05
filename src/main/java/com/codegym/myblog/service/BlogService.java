package com.codegym.myblog.service;

import com.codegym.myblog.model.Blog;
import com.codegym.myblog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {

    Page<Blog> findAll(Pageable pageable);

    Blog findById(int id);

    void save(Blog blog);

    void remove(int id);

    Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAllByFirstNameContaining(String firstname, Pageable pageable);
}
