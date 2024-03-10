package ru.practicum.ewm.service.controller.publicZone;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.CategoryViewDto;
import ru.practicum.ewm.service.dto.EventSort;
import ru.practicum.ewm.service.dto.EventViewFullDto;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventPublicController {

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
