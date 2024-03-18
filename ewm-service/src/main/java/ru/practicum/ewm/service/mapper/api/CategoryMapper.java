package ru.practicum.ewm.service.mapper.api;

import ru.practicum.ewm.service.dto.category.CategoryCreateRequestDto;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.model.Category;

public interface CategoryMapper {
    Category toCategory(final CategoryCreateRequestDto dto);

    CategoryDto toCategoryDto(final Category category);
}
