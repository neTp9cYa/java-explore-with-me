package ru.practicum.stat.service.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stat.dto.HitCreateDto;
import ru.practicum.stat.dto.StatItemViewDto;
import ru.practicum.stat.service.service.StatService;

@RestController
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void addHit(@RequestBody final HitCreateDto hitCreateDto) {
        statService.addHit(hitCreateDto);
    }

    @GetMapping("/stats")
    public List<StatItemViewDto> getStats() {
        return statService.getStats();
    }
}
