package ru.practicum.stats.client.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class StatsRequestDto {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private @Nullable List<String> uris;
    private @Nullable Boolean unique;

    public StatsRequestDto(final LocalDateTime start, final LocalDateTime end) {
        this.start = start;
        this.end = end;
    }
}
