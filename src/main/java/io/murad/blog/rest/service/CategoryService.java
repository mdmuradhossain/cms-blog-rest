package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.CategoryDto;
import io.murad.blog.rest.mapper.CategoryMapper;
import io.murad.blog.rest.model.Category;
import io.murad.blog.rest.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public void saveCategory(CategoryDto categoryDto) {
        Category saveCategory = categoryRepository.save(categoryMapper.mapDtoToCategory(categoryDto));
        categoryDto.setId(saveCategory.getCategoryId());
    }
}
