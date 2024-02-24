package ru.practicum.stat.service.service;

import org.springframework.stereotype.Component;
import ru.practicum.stat.dto.HitCreateDto;
import ru.practicum.stat.service.model.Hit;

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
