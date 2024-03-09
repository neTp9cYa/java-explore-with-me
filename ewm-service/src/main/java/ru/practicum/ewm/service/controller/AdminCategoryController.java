package ru.practicum.ewm.service.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.CategoryCreateRequestDto;
import ru.practicum.ewm.service.dto.CategoryUpdateRequestDto;
import ru.practicum.ewm.service.dto.CategoryViewDto;

@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @PostMapping()
    public CategoryViewDto create(@RequestBody final CategoryCreateRequestDto categoryCreateRequestDto) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable final long categoryId) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/{categoryId}")
    public CategoryViewDto update(@PathVariable final long categoryId,
                                  @RequestBody final CategoryUpdateRequestDto categoryUpdateRequestDto) {
        throw new UnsupportedOperationException();
    }
}
