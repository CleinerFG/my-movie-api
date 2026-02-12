package com.mymovie.api.mapper;

import com.mymovie.api.dto.request.CategoryRequest;
import com.mymovie.api.dto.response.CategoryResponse;
import com.mymovie.api.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequest dto) {
        var category = new Category();
        updateEntityFromDTO(dto, category);
        return category;
    }

    public CategoryResponse toResponseDTO(Category entity) {
        return CategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public void updateEntityFromDTO(CategoryRequest dto, Category entity) {
        entity.setName(dto.name());
    }

    public void patchEntityFromDTO(CategoryRequest dto, Category entity) {
        if (dto.name() != null) entity.setName(dto.name());
    }
}
