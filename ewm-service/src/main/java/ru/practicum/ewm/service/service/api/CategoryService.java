package ru.practicum.ewm.service.service.api;

import ru.practicum.ewm.service.dto.category.CategoryCreateRequestDto;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.dto.category.CategoryUpdateRequestDto;

public interface CategoryService {
    CategoryDto create(final CategoryCreateRequestDto categoryDto);
    CategoryDto update(final long categoryId, final CategoryUpdateRequestDto categoryDto);
    void delete(final long categoryId);
}
