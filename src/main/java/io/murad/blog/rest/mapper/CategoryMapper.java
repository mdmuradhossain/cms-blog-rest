package io.murad.blog.rest.mapper;

import io.murad.blog.rest.dto.CategoryDto;
import io.murad.blog.rest.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(target = "id", source = "category.categoryId"),
            @Mapping(target = "name", source = "category.categoryName")
    })
    public CategoryDto mapCategoryToDto(Category category);

    @Mappings({
            @Mapping(target = "categoryId", source = "categoryDto.id"),
            @Mapping(target = "categoryName", source = "categoryDto.name"),
            @Mapping(target = "posts", ignore = true)
    })
    public Category mapDtoToCategory(CategoryDto categoryDto);
}
