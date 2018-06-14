package com.codegym.myblog.service;

import com.codegym.myblog.model.User;

public interface UserService {
    User getById(int id);
    public User findByUsername(String username);
}
