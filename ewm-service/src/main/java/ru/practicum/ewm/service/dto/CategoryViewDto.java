package ru.practicum.ewm.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryViewDto {
    private long id;
    private String name;
}
