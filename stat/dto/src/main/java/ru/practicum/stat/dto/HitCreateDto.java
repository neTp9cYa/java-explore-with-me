package ru.practicum.stat.dto;

import java.time.Instant;
import java.time.LocalDateTime;
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
