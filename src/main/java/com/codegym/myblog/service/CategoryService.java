package com.codegym.myblog.service;

import com.codegym.myblog.model.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {

    Iterable<Category> findAll();

    void save(Category customer);

    void remove(int id);

    Category findById(int id);
}
