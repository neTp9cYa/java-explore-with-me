package ru.practicum.stats.client;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.stats.client.dto.StatsRequestDto;
import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.dto.StatItemViewDto;

@Slf4j
@Service
public class StatsClientImpl extends BaseClient implements StatsClient {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public StatsClientImpl(@Value("${stats-server.url}") final String serverUrl, final RestTemplateBuilder builder) {
        super(builder
            .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
            .requestFactory(HttpComponentsClientHttpRequestFactory::new)
            .build());

    }

    public void addHit(final HitCreateDto hitCreateDto) throws HttpStatusCodeException {
        post("/hit", hitCreateDto, Object.class);
    }

    public List<StatItemViewDto> getStats(final StatsRequestDto statsRequestDto) throws HttpStatusCodeException {
        if (statsRequestDto.getStart() == null) {
            throw new IllegalArgumentException("Start must be set");
        }
        if (statsRequestDto.getEnd() == null) {
            throw new IllegalArgumentException("End must be set");
        }

        final Map<String, Object> parameters = new HashMap<>(Map.of(
            "start", statsRequestDto.getStart().format(DATE_TIME_FORMATTER),
            "end", statsRequestDto.getEnd().format(DATE_TIME_FORMATTER)
        ));

        if (statsRequestDto.getUris() != null) {
            parameters.put("uris", String.join(",", statsRequestDto.getUris()));
        }

        if (statsRequestDto.getUnique() != null) {
            parameters.put("unique", statsRequestDto.getUnique());
        }

        final String path = parameters.keySet().stream()
            .map(key -> String.format("%s={%s}", key, key))
            .collect(Collectors.joining("&", "stats?", ""));

        final ResponseEntity<StatItemViewDto[]> response = get(path, parameters, StatItemViewDto[].class);

        return response.getStatusCode().is2xxSuccessful()
            ? Arrays.asList(response.getBody())
            : Collections.<StatItemViewDto>emptyList();
    }
}
