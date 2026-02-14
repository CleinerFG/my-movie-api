package com.mymovie.api.mapper;

import com.mymovie.api.dto.request.CategoryRequest;
import com.mymovie.api.dto.response.CategoryResponse;
import com.mymovie.api.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequest dto) {
        return Category.builder()
                .name(dto.name())
                .build();
    }

    public CategoryResponse toResponseDTO(Category entity) {
        return CategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
