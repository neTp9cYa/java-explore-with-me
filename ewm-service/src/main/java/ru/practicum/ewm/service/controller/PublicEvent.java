package ru.practicum.ewm.service.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.service.dto.CategoryViewDto;
import ru.practicum.ewm.service.dto.EventSort;
import ru.practicum.ewm.service.dto.EventViewFullDto;

@RequestMapping("/events")
public class PublicEvent {

    @GetMapping("/{eventId}")
    public EventViewFullDto getEvent(@PathVariable final long categoryId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<CategoryViewDto> search(@RequestParam final String text,
                                        @RequestParam final List<Long> categories,
                                        @RequestParam final Boolean paid,
                                        @RequestParam final LocalDateTime rangeStart,
                                        @RequestParam final LocalDateTime rangeEnd,
                                        @RequestParam final Boolean onlyAvailable,
                                        @RequestParam final EventSort sort,
                                        @RequestParam(defaultValue = "0") final long from,
                                        @RequestParam(defaultValue = "10") final long size) {
        throw new UnsupportedOperationException();
    }
}
