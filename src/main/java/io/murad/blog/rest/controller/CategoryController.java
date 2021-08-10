package io.murad.blog.rest.controller;

import io.murad.blog.rest.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto){
        categoryService.saveCategory(categoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
