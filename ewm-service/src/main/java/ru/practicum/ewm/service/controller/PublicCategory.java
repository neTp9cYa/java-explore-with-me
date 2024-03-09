package ru.practicum.ewm.service.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.service.dto.CategoryViewDto;

@RequestMapping("/categories")
public class PublicCategory {

    @GetMapping("/{categoryId}")
    public CategoryViewDto getCategory(@PathVariable final long categoryId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<CategoryViewDto> getCategories(@RequestParam(defaultValue = "0") final long from,
                                               @RequestParam(defaultValue = "10") final long size) {
        throw new UnsupportedOperationException();
    }
}
