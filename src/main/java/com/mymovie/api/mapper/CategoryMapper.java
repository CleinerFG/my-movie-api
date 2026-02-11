package com.mymovie.api.mapper;

import com.mymovie.api.dto.CategoryRequestDTO;
import com.mymovie.api.dto.CategoryResponseDTO;
import com.mymovie.api.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        updateEntityFromDTO(dto, category);
        return category;
    }

    public CategoryResponseDTO toResponseDTO(Category entity) {
        return new CategoryResponseDTO(entity.getId(), entity.getName());
    }

    public void updateEntityFromDTO(CategoryRequestDTO dto, Category entity) {
        entity.setName(dto.name());
    }
}
