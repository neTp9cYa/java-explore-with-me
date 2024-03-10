package ru.practicum.ewm.service.controller.publicArea;

import java.util.List;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.service.api.CategoryService;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryPublicController {

    private final CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public CategoryDto getCategory(@PathVariable final long categoryId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<CategoryDto> getCategories(@RequestParam(defaultValue = "0") @PositiveOrZero final long from,
                                           @RequestParam(defaultValue = "10") @Positive final long size) {
        throw new UnsupportedOperationException();
    }
}
