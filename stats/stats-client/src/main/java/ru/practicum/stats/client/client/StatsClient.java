package ru.practicum.stats.client.client;

import java.util.List;
import org.springframework.web.client.HttpStatusCodeException;
import ru.practicum.stats.client.request.GetStatsRequest;
import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.dto.StatItemDto;

public interface StatsClient {

    void addHit(final HitCreateDto hitCreateDto) throws HttpStatusCodeException;

    List<StatItemDto> getStats(final GetStatsRequest getStatsRequest) throws HttpStatusCodeException;
}
