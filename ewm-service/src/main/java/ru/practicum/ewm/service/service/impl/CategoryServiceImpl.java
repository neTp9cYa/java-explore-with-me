package ru.practicum.ewm.service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.dto.category.CategoryCreateRequestDto;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.dto.category.CategoryUpdateRequestDto;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.mapper.api.CategoryMapper;
import ru.practicum.ewm.service.model.Category;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.repository.CategoryRepository;
import ru.practicum.ewm.service.service.api.CategoryService;
import ru.practicum.ewm.service.service.request.GetCategoriesRequest;
import ru.practicum.utils.pagination.FlexPageRequest;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDto create(final CategoryCreateRequestDto categoryDto) {
        final Category creatingCategory = categoryMapper.toCategory(categoryDto);
        final Category createdCategory = categoryRepository.save(creatingCategory);
        return categoryMapper.toCategoryDto(createdCategory);
    }

    @Override
    @Transactional
    public CategoryDto update(final long categoryId, final CategoryUpdateRequestDto categoryDto) {
        final Category updateingCategory = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new NotFoundException(String.format("Category with id %d not found", categoryId)));

        updateingCategory.setName(categoryDto.getName());
        final Category updatedCategory = categoryRepository.save(updateingCategory);

        return categoryMapper.toCategoryDto(updatedCategory);
    }

    @Override
    @Transactional
    public void delete(final long categoryId) {
        final Category deletingCategory = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new NotFoundException(String.format("Category with id %d not found", categoryId)));

        categoryRepository.delete(deletingCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategory(long categoryId) {
        final Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new NotFoundException(String.format("Category with id %d not found", categoryId)));

        return categoryMapper.toCategoryDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getCategories(GetCategoriesRequest getCategoriesRequest) {
        final Pageable pageable = FlexPageRequest.of(getCategoriesRequest.getFrom(), getCategoriesRequest.getSize());

        final Page<Category> categoryPage = categoryRepository.findAll(pageable);

        return categoryPage.stream()
            .map(category ->categoryMapper.toCategoryDto(category))
            .collect(Collectors.toList());
    }
}
