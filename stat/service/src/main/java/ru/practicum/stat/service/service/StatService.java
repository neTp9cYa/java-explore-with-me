package ru.practicum.stat.service.service;

import java.util.ArrayList;
import java.util.List;
import ru.practicum.stat.dto.HitCreateDto;
import ru.practicum.stat.dto.StatItemViewDto;
import ru.practicum.stat.service.model.Hit;

public interface StatService {

    Hit addHit(final HitCreateDto hitCreateDto);
    List<StatItemViewDto> getStats();
}
