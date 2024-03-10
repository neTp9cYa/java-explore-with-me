package ru.practicum.ewm.service.dto.category;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryCreateRequestDto {
    @NotBlank
    @Max(50)
    private String name;
}
