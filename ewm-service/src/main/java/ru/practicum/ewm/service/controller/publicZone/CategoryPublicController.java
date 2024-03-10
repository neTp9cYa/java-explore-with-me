package ru.practicum.ewm.service.controller.publicZone;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.CategoryViewDto;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryPublicController {

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
