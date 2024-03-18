package ru.practicum.stats.client.service;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.practicum.stats.client.client.StatsClient;
import ru.practicum.stats.dto.HitCreateDto;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatsClient statsClient;

    @Value("${application.name}")
    private String applicationName;

    @Override
    public void addHit(final HttpServletRequest request) {
        final HitCreateDto hitCreateDto = HitCreateDto.builder()
            .uri(request.getRequestURI())
            .app(applicationName)
            .timestamp(LocalDateTime.now())
            .ip(request.getRemoteAddr())
            .build();

        statsClient.addHit(hitCreateDto);
    }
}
