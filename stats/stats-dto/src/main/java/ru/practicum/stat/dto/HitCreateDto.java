package ru.practicum.stat.dto;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HitCreateDto {
    private String app;
    private String uri;
    private String ip;
    private Instant timestamp;
}
