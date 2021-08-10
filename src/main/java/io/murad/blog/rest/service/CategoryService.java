package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.CategoryDto;
import io.murad.blog.rest.exception.BlogRestException;
import io.murad.blog.rest.mapper.CategoryMapper;
import io.murad.blog.rest.model.Category;
import io.murad.blog.rest.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map((category) ->
                        categoryMapper.mapCategoryToDto(category)
                ).collect(Collectors.toList());
    }

    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new BlogRestException("No category found with this " + id));
        return categoryMapper.mapCategoryToDto(category);
    }
}
