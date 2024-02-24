package ru.practicum.stats.server.service;

import java.util.List;
import ru.practicum.stat.dto.HitCreateDto;
import ru.practicum.stat.dto.StatItemViewDto;
import ru.practicum.stats.server.model.Hit;

public interface StatService {

    Hit addHit(final HitCreateDto hitCreateDto);

    List<StatItemViewDto> getStats();
}
