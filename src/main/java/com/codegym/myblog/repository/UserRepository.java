package com.codegym.myblog.repository;

import com.codegym.myblog.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    public User findUserByUsername(String username);
}
