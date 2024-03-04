package ru.practicum.stats.server.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.stats.dto.StatItemViewDto;
import ru.practicum.stats.server.model.Hit;

@Repository
public interface HitRepository extends JpaRepository<Hit, Integer> {
    @Query("SELECT new ru.practicum.stats.dto.StatItemViewDto(h.app, h.uri, count(h.ip)) " +
           "FROM Hit h " +
           "WHERE h.timestamp BETWEEN :start AND :end " +
           "GROUP BY h.app, h.uri")
    List<StatItemViewDto> getHitsStats(final LocalDateTime start,
                                       final LocalDateTime end);
    @Query("SELECT new ru.practicum.stats.dto.StatItemViewDto(h.app, h.uri, count(h.ip)) " +
        "FROM Hit h " +
        "WHERE (h.timestamp BETWEEN :start AND :end) AND (h.uri IN :urisOptional) " +
        "GROUP BY h.app, h.uri")
    List<StatItemViewDto> getHitsStats(final LocalDateTime start,
                                       final LocalDateTime end,
                                       final List<String> urisOptional);

    @Query("SELECT new ru.practicum.stats.dto.StatItemViewDto(h.app, h.uri, count(distinct h.ip)) " +
        "FROM Hit h " +
        "WHERE h.timestamp BETWEEN :start AND :end " +
        "GROUP BY h.app, h.uri")
    List<StatItemViewDto> getUniqueHitsStats(final LocalDateTime start,
                                             final LocalDateTime end);

    @Query("SELECT new ru.practicum.stats.dto.StatItemViewDto(h.app, h.uri, count(distinct h.ip)) " +
        "FROM Hit h " +
        "WHERE (h.timestamp BETWEEN :start AND :end) AND (:urisOptional IN :urisOptional) " +
        "GROUP BY h.app, h.uri")
    List<StatItemViewDto> getUniqueHitsStats(final LocalDateTime start,
                                             final LocalDateTime end,
                                             final List<String> urisOptional);
}
