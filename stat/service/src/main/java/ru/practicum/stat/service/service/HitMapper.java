package ru.practicum.stat.service.service;

import ru.practicum.stat.dto.HitCreateDto;
import ru.practicum.stat.service.model.Hit;

public interface HitMapper {
    Hit toHit(final HitCreateDto hitCreateDto);
}
