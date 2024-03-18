package ru.practicum.ewm.service.dto.category;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryDto {
    private long id;
    private String name;
}
