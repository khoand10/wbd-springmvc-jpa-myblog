package com.codegym.myblog.repository;

import com.codegym.myblog.model.Myblog;

import java.util.List;

public interface MyblogRepository {
    List<Myblog> getAllBlog();
    void save(Myblog myblog);
    void remove(int id);
    void update (Myblog myblog);
    Myblog getBlog(int id);
}
