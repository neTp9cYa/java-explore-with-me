package ru.practicum.ewm.service.dto.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CategoryCreateRequestDto {
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
}
