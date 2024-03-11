package ru.practicum.ewm.service.service.api;

import java.util.List;
import ru.practicum.ewm.service.dto.category.CategoryCreateRequestDto;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.dto.category.CategoryUpdateRequestDto;
import ru.practicum.ewm.service.service.request.GetCategoriesRequest;

public interface CategoryService {
    CategoryDto create(final CategoryCreateRequestDto categoryDto);
    CategoryDto update(final long categoryId, final CategoryUpdateRequestDto categoryDto);
    void delete(final long categoryId);
    CategoryDto getCategory(final long categoryId);
    List<CategoryDto> getCategories(final GetCategoriesRequest getCategoriesRequest);
}
