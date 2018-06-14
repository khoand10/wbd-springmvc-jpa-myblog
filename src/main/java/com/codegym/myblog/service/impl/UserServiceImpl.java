package com.codegym.myblog.service.impl;

import com.codegym.myblog.model.User;
import com.codegym.myblog.repository.UserRepository;
import com.codegym.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
