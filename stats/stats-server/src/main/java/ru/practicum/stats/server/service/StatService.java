package ru.practicum.stats.server.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.dto.StatItemViewDto;
import ru.practicum.stats.server.model.Hit;

public interface StatService {

    Hit addHit(final HitCreateDto hitCreateDto);

    List<StatItemViewDto> getStats(final LocalDateTime start,
                                   final LocalDateTime end,
                                   final Optional<List<String>> urisOptional,
                                   final Boolean unique);
}
