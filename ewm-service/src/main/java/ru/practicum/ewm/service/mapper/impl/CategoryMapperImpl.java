package ru.practicum.ewm.service.mapper.impl;

import org.springframework.stereotype.Component;
import ru.practicum.ewm.service.dto.category.CategoryCreateRequestDto;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.mapper.api.CategoryMapper;
import ru.practicum.ewm.service.model.Category;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(final CategoryCreateRequestDto dto) {
        return Category.builder()
            .name(dto.getName())
            .build();
    }

    @Override
    public CategoryDto toCategoryDto(final Category category) {
        return CategoryDto.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }
}
