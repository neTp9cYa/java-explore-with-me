package ru.practicum.stats.server.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.stat.dto.HitCreateDto;
import ru.practicum.stat.dto.StatItemViewDto;
import ru.practicum.stats.server.model.Hit;
import ru.practicum.stats.server.repository.HitRepository;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final HitMapper hitMapper;
    private final HitRepository hitRepository;

    @Override
    public Hit addHit(HitCreateDto hitCreateDto) {
        final Hit hit = hitMapper.toHit(hitCreateDto);
        hitRepository.save(hit);
        return hit;
    }

    @Override
    public List<StatItemViewDto> getStats() {
        return new ArrayList<StatItemViewDto>();
    }
}
