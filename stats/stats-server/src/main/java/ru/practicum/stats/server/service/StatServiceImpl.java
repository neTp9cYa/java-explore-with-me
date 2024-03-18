package ru.practicum.stats.server.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.dto.StatItemDto;
import ru.practicum.stats.server.model.Hit;
import ru.practicum.stats.server.repository.HitRepository;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final HitMapper hitMapper;
    private final HitRepository hitRepository;

    @Override
    @Transactional
    public Hit addHit(HitCreateDto hitCreateDto) {
        final Hit hit = hitMapper.toHit(hitCreateDto);
        hitRepository.save(hit);
        return hit;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatItemDto> getStats(final LocalDateTime start,
                                      final LocalDateTime end,
                                      final Optional<List<String>> urisOptional,
                                      final Boolean unique) {

        return unique
            ? (urisOptional.isEmpty()
            ? hitRepository.getUniqueHitsStats(start, end)
            : hitRepository.getUniqueHitsStats(start, end, urisOptional.get()))
            : (urisOptional.isEmpty()
            ? hitRepository.getHitsStats(start, end)
            : hitRepository.getHitsStats(start, end, urisOptional.get()));
    }
}
