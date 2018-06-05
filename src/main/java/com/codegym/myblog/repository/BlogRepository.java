package com.codegym.myblog.repository;

import com.codegym.myblog.model.Blog;
import com.codegym.myblog.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Integer> {

    Iterable<Blog> findAllByCategory(Category category);

}
