package ru.practicum.ewm.service.controller.privateZone;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.EventViewDto;
import ru.practicum.ewm.service.dto.EventViewFullDto;
import ru.practicum.ewm.service.dto.ParticipationRequestViewDto;

@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
public class EventPrivateController {

    @PostMapping
    public EventViewFullDto create(@PathVariable final long userId,
                                   @RequestBody final EventCreateRequestDto eventCreateRequestDto) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/{eventId}")
    public EventViewFullDto update(@PathVariable final long userId,
                                   @PathVariable final long eventId,
                                   @RequestBody final EventCreateRequestDto eventCreateRequestDto) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{eventId}")
    public List<EventViewDto> get(@PathVariable final long userId,
                                  @PathVariable final long eventId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<EventViewDto> getList(@PathVariable final long userId,
                                      @RequestParam(defaultValue = "0") final long from,
                                      @RequestParam(defaultValue = "10") final long size) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestViewDto> updateRequests(@PathVariable final long userId,
                                                            @RequestParam final List<Long> eventId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestViewDto> getRequests(@PathVariable final long userId,
                                                         @PathVariable final long eventId) {
        throw new UnsupportedOperationException();
    }
}
