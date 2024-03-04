package ru.practicum.stats.server.service;

import org.springframework.stereotype.Component;
import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.server.model.Hit;

@Component
public class HitMapperImpl implements HitMapper {

    @Override
    public Hit toHit(HitCreateDto hitCreateDto) {
        return Hit.builder()
            .app(hitCreateDto.getApp())
            .uri(hitCreateDto.getUri())
            .ip(hitCreateDto.getIp())
            .timestamp(hitCreateDto.getTimestamp())
            .build();
    }
}
