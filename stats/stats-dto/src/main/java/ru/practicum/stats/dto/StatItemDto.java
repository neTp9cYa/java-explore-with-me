package ru.practicum.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class StatItemDto {
    private String app;
    private String uri;
    private long hits;
}
