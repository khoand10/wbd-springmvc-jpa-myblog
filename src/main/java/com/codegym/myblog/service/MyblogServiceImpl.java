package com.codegym.myblog.service;

import com.codegym.myblog.model.Myblog;
import com.codegym.myblog.repository.MyblogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyblogServiceImpl implements MyblogService {

    @Autowired
    MyblogRepository myblogRepository;

    @Override
    public List<Myblog> getAllBlog() {
        return myblogRepository.getAllBlog();
    }

    @Override
    public void save(Myblog myblog) {
        myblogRepository.save(myblog);
    }

    @Override
    public void remove(int id) {
        myblogRepository.remove(id);
    }

    @Override
    public void update(Myblog myblog) {
        myblogRepository.update(myblog);
    }

    @Override
    public Myblog getBlog(int id) {
        return myblogRepository.getBlog(id);
    }
}
