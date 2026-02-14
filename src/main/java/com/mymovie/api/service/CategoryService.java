package com.mymovie.api.service;

import com.mymovie.api.dto.request.CategoryRequest;
import com.mymovie.api.dto.response.CategoryResponse;
import com.mymovie.api.entity.Category;
import com.mymovie.api.infra.exception.ResourceNotFoundException;
import com.mymovie.api.mapper.CategoryMapper;
import com.mymovie.api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse create(CategoryRequest dto) {
        var category = categoryMapper.toEntity(dto);
        var savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponseDTO(savedCategory);
    }

    public List<CategoryResponse> findAll() {
        var categories = categoryRepository.findAll();

        return categories.stream()
                .map(categoryMapper::toResponseDTO)
                .toList();
    }

    public CategoryResponse findById(Long id) {
        Optional<Category> optCategory = categoryRepository.findById(id);

        return optCategory
                .map(categoryMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("categoryNotFound"));
    }

    public CategoryResponse updateById(Long id, CategoryRequest dto) {
        Optional<Category> optCategory = categoryRepository.findById(id);

        return optCategory.map(category -> {
            patchUpdate(category, dto);
            var savedCategory = categoryRepository.save(category);

            return categoryMapper.toResponseDTO(savedCategory);
        }).orElseThrow(() -> new ResourceNotFoundException("categoryNotFound"));
    }

    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("categoryNotFound");
        }

        categoryRepository.deleteById(id);
    }

    public List<Category> findAllEntitiesByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptyList();

        return categoryRepository.findAllById(ids);
    }

    private void patchUpdate(Category entity, CategoryRequest dto) {
        if (dto.name() != null) entity.setName(dto.name());
    }
}
