package ru.practicum.stats.client;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.dto.StatItemViewDto;

public class StatsClient extends BaseClient {

    public StatsClient(@Value("${stats-server.url}") final String serverUrl, final RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build());
    }

    public void addHit(final HitCreateDto hitCreateDto) throws HttpStatusCodeException {
        post("/hit", hitCreateDto, Object.class);
    }

    public List<StatItemViewDto> getStats(final LocalDateTime start,
                                          final LocalDateTime end) throws HttpStatusCodeException  {
        return getStats(start, end, Optional.empty(), Optional.empty());
    }

    public List<StatItemViewDto> getStats(final LocalDateTime start,
                                          final LocalDateTime end,
                                          final Boolean unique) throws HttpStatusCodeException {
        return getStats(start, end, Optional.empty(), Optional.of(unique));
    }

    public List<StatItemViewDto> getStats(final LocalDateTime start,
                                          final LocalDateTime end,
                                          final List<String> uris) throws HttpStatusCodeException  {
        return getStats(start, end, Optional.of(uris), Optional.empty());
    }

    public List<StatItemViewDto> getStats(final LocalDateTime start,
                                          final LocalDateTime end,
                                          final List<String> uris,
                                          final Boolean unique) throws HttpStatusCodeException  {
        return getStats(start, end, Optional.of(uris), Optional.of(unique));
    }

    private List<StatItemViewDto> getStats(final LocalDateTime start,
                                           final LocalDateTime end,
                                           final Optional<List<String>> urisOptional,
                                           final Optional<Boolean> uniqueOptional) throws HttpStatusCodeException  {
        final Map<String, Object> parameters = Map.of(
            "start", start,
            "end", end
        );
        urisOptional.ifPresent(uris -> parameters.put("uris", String.join(",", uris)));
        uniqueOptional.ifPresent(unique -> parameters.put("unique", unique));

        final String path = parameters.keySet().stream()
            .map(key -> String.format("%s={%s}", key, key))
            .collect(Collectors.joining("&", "stats?", ""));

        final ResponseEntity<List<StatItemViewDto>> response = get(path, parameters,
            (Class<List<StatItemViewDto>>) Collections.<StatItemViewDto>emptyList().getClass());

        return response.getBody();
    }
}
