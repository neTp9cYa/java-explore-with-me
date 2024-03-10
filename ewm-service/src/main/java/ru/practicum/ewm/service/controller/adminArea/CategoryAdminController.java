package ru.practicum.ewm.service.controller.adminArea;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.category.CategoryCreateRequestDto;
import ru.practicum.ewm.service.dto.category.CategoryUpdateRequestDto;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.service.api.CategoryService;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PostMapping()
    public CategoryDto create(@RequestBody final CategoryCreateRequestDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @PatchMapping("/{categoryId}")
    public CategoryDto update(@PathVariable final long categoryId,
                              @RequestBody final CategoryUpdateRequestDto categoryDto) {
        return categoryService.update(categoryId, categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final long categoryId) {
        categoryService.delete(categoryId);
    }
}
