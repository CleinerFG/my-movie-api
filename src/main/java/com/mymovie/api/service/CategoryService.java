package com.mymovie.api.service;

import com.mymovie.api.dto.request.CategoryRequest;
import com.mymovie.api.dto.response.CategoryResponse;
import com.mymovie.api.entity.Category;
import com.mymovie.api.mapper.CategoryMapper;
import com.mymovie.api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse create(CategoryRequest dto) {
        Category category = categoryMapper.toEntity(dto);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponseDTO(savedCategory);
    }

    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(categoryMapper::toResponseDTO)
                .toList();
    }

    public CategoryResponse findById(Long id) {
        Optional<Category> optCategory = categoryRepository.findById(id);

        return optCategory
                .map(categoryMapper::toResponseDTO)
                .orElse(null);
    }

    public CategoryResponse updateById(Long id, CategoryRequest dto) {
        Optional<Category> optCategory = categoryRepository.findById(id);

        return optCategory.map(category->{
            categoryMapper.patchEntityFromDTO(dto, category);
            Category savedCategory = categoryRepository.save(category);

            return categoryMapper.toResponseDTO(savedCategory);
        }).orElse(null);
    }

    public boolean deleteById(Long id) {
        if (!categoryRepository.existsById(id)) return false;

        categoryRepository.deleteById(id);
        return true;
    }
}
