package ru.practicum.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatItemViewDto {
    private String app;
    private String uri;
    private long hits;
}
