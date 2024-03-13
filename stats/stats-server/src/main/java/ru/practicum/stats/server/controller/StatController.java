package ru.practicum.stats.server.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stats.dto.HitCreateDto;
import ru.practicum.stats.dto.StatItemDto;
import ru.practicum.stats.server.service.StatService;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequiredArgsConstructor
@Validated
public class StatController {

    private final StatService statService;

    @PostMapping("/hit")
    @LogInputOutputAnnotaion
    @ResponseStatus(HttpStatus.CREATED)
    public void addHit(@RequestBody @Valid final HitCreateDto hitCreateDto) {
        statService.addHit(hitCreateDto);
    }

    @GetMapping("/stats")
    @LogInputOutputAnnotaion
    public List<StatItemDto> getStats(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final LocalDateTime start,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final LocalDateTime end,
        @RequestParam final Optional<List<String>> uris,
        @RequestParam(defaultValue = "false") final Boolean unique) {

        return statService.getStats(start, end, uris, unique);
    }
}
