package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.CategoryDto;
import io.murad.blog.rest.exception.BlogRestException;
import io.murad.blog.rest.mapper.CategoryMapper;
import io.murad.blog.rest.model.Category;
import io.murad.blog.rest.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public void saveCategory(CategoryDto categoryDto) {
        Category saveCategory = categoryRepository.save(categoryMapper.mapDtoToCategory(categoryDto));
        log.info("Category Saved..." + saveCategory.getCategoryName());
//        categoryDto.setId(saveCategory.getId());
        saveCategory.setId(categoryDto.getId());
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
