package ru.practicum.stats.server.service;

import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.server.model.Hit;

public interface HitMapper {
    Hit toHit(final HitCreateDto hitCreateDto);
}
