package ru.practicum.ewm.service.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stats.client.StatsClient;
import ru.practicum.stats.client.request.GetStatsRequest;
import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.dto.StatItemDto;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequiredArgsConstructor
public class StatController {

    private final StatsClient statsClient;

    @PostMapping("/hit")
    @LogInputOutputAnnotaion
    @ResponseStatus(HttpStatus.CREATED)
    public void addHit(@RequestBody @Valid final HitCreateDto hitCreateDto) {
        statsClient.addHit(hitCreateDto);
    }

    @GetMapping("/stats")
    @LogInputOutputAnnotaion
    public List<StatItemDto> getStats(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final LocalDateTime start,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final LocalDateTime end,
        @RequestParam final Optional<List<String>> uris,
        @RequestParam final Optional<Boolean> unique) {

        final GetStatsRequest getStatsRequest = new GetStatsRequest(start, end);
        uris.ifPresent(getStatsRequest::setUris);
        unique.ifPresent(getStatsRequest::setUnique);

        return statsClient.getStats(getStatsRequest);
    }
}
