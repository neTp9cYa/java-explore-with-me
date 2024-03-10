package ru.practicum.ewm.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.service.dto.category.CategoryCreateRequestDto;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.dto.category.CategoryUpdateRequestDto;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.mapper.api.CategoryMapper;
import ru.practicum.ewm.service.model.Category;
import ru.practicum.ewm.service.repository.CategoryRepository;
import ru.practicum.ewm.service.service.api.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto create(final CategoryCreateRequestDto categoryDto) {
        final Category creatingCategory = categoryMapper.toCategory(categoryDto);
        final Category createdCategory = categoryRepository.save(creatingCategory);
        return categoryMapper.toCategoryDto(createdCategory);
    }

    @Override
    public CategoryDto update(final long categoryId, final CategoryUpdateRequestDto categoryDto) {
        final Category updateingCategory = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new NotFoundException(String.format("Category with id %d not found", categoryId)));

        updateingCategory.setName(categoryDto.getName());
        final Category updatedCategory = categoryRepository.save(updateingCategory);

        return categoryMapper.toCategoryDto(updatedCategory);
    }

    @Override
    public void delete(final long categoryId) {
        final Category deletingCategory = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new NotFoundException(String.format("Category with id %d not found", categoryId)));

        categoryRepository.delete(deletingCategory);
    }


}
