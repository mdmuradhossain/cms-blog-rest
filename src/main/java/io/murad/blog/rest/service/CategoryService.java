package io.murad.blog.rest.service;

import io.murad.blog.rest.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void saveCategory(){

    }
}
