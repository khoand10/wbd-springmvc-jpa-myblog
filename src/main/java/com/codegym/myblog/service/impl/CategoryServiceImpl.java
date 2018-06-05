package com.codegym.myblog.service.impl;

import com.codegym.myblog.model.Category;
import com.codegym.myblog.repository.CategoryRepository;
import com.codegym.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category customer) {
        categoryRepository.save(customer);
    }

    @Override
    public void remove(int id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findOne(id);
    }

}
