package ru.practicum.ewm.service.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.service.dto.EventState;
import ru.practicum.ewm.service.dto.EventUpdateAdminRequestDto;
import ru.practicum.ewm.service.dto.EventViewFullDto;

@RequestMapping("/admin/events")
public class AdminEvent {

    @PatchMapping("/{eventId}")
    public EventViewFullDto update(@PathVariable final int eventId,
                                   @RequestBody final EventUpdateAdminRequestDto eventUpdateAdminRequestDto) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<EventViewFullDto> search(@RequestParam final List<Long> users,
                                         @RequestParam final List<EventState> states,
                                         @RequestParam final List<Long> categories,
                                         @RequestParam final LocalDateTime rangeStart,
                                         @RequestParam final LocalDateTime rangeEnd,
                                         @RequestParam(defaultValue = "0") final long from,
                                         @RequestParam(defaultValue = "10") final long size) {
        throw new UnsupportedOperationException();
    }

}
