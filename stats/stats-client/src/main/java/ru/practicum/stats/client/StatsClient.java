package ru.practicum.stats.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.dto.StatItemViewDto;

@Service
public class StatsClient extends BaseClient {

    final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public StatsClient(@Value("${stats-server.url}") final String serverUrl, final RestTemplateBuilder builder) {
        super(builder
            .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
            .requestFactory(HttpComponentsClientHttpRequestFactory::new)
            .build());
    }

    public void addHit(final HitCreateDto hitCreateDto) throws HttpStatusCodeException {
        post("/hit", hitCreateDto, Object.class);
    }

    public StatItemViewDto[] getStats(final LocalDateTime start,
                                      final LocalDateTime end) throws HttpStatusCodeException {
        return getStats(start, end, Optional.empty(), Optional.empty());
    }

    public StatItemViewDto[] getStats(final LocalDateTime start,
                                      final LocalDateTime end,
                                      final Boolean unique) throws HttpStatusCodeException {
        return getStats(start, end, Optional.empty(), Optional.of(unique));
    }

    public StatItemViewDto[] getStats(final LocalDateTime start,
                                      final LocalDateTime end,
                                      final List<String> uris) throws HttpStatusCodeException {
        return getStats(start, end, Optional.of(uris), Optional.empty());
    }

    public StatItemViewDto[] getStats(final LocalDateTime start,
                                      final LocalDateTime end,
                                      final List<String> uris,
                                      final Boolean unique) throws HttpStatusCodeException {
        return getStats(start, end, Optional.of(uris), Optional.of(unique));
    }

    private StatItemViewDto[] getStats(final LocalDateTime start,
                                       final LocalDateTime end,
                                       final Optional<List<String>> urisOptional,
                                       final Optional<Boolean> uniqueOptional) throws HttpStatusCodeException {

        final Map<String, Object> parameters = new HashMap<>(Map.of(
            "start", start.format(DATE_TIME_FORMATTER),
            "end", end.format(DATE_TIME_FORMATTER)
        ));
        urisOptional.ifPresent(uris -> parameters.put("uris", String.join(",", uris)));
        uniqueOptional.ifPresent(unique -> parameters.put("unique", unique));

        final String path = parameters.keySet().stream()
            .map(key -> String.format("%s={%s}", key, key))
            .collect(Collectors.joining("&", "stats?", ""));

        final ResponseEntity<StatItemViewDto[]> response = get(path, parameters, StatItemViewDto[].class);

        return response.getBody();
    }
}
