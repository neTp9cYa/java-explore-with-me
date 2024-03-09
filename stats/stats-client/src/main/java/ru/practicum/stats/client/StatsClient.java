package ru.practicum.stats.client;

import java.util.List;
import org.springframework.web.client.HttpStatusCodeException;
import ru.practicum.stats.client.dto.StatsRequestDto;
import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.dto.StatItemViewDto;

public interface StatsClient {

    void addHit(final HitCreateDto hitCreateDto) throws HttpStatusCodeException;

    List<StatItemViewDto> getStats(final StatsRequestDto statsRequestDto) throws HttpStatusCodeException;
}
