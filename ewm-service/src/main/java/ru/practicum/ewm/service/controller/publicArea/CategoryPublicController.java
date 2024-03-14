package ru.practicum.ewm.service.controller.publicArea;

import java.util.List;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.service.api.CategoryService;
import ru.practicum.ewm.service.service.request.GetCategoriesRequest;
import ru.practicum.stats.client.aspect.CollectRequestStatisticAnnotaion;
import ru.practicum.stats.client.service.StatService;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryPublicController {

    private final CategoryService categoryService;
    private final StatService statService;

    @GetMapping("/{categoryId}")
    @LogInputOutputAnnotaion
    @CollectRequestStatisticAnnotaion
    public CategoryDto getCategory(@PathVariable final long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @GetMapping
    @LogInputOutputAnnotaion
    @CollectRequestStatisticAnnotaion
    public List<CategoryDto> getCategories(@RequestParam(defaultValue = "0") @PositiveOrZero final long from,
                                           @RequestParam(defaultValue = "10") @Positive final int size) {
        final GetCategoriesRequest getCategoriesRequest = GetCategoriesRequest.builder()
            .from(from)
            .size(size)
            .build();

        return categoryService.getCategories(getCategoriesRequest);
    }
}
