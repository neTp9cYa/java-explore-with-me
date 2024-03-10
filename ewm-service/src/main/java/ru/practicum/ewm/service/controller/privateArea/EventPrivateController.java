package ru.practicum.ewm.service.controller.privateArea;

import java.util.List;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.event.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestViewDto;
import ru.practicum.ewm.service.service.api.EventService;

@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
public class EventPrivateController {

    private final EventService eventService;

    @PostMapping
    public EventFullDto create(@PathVariable final long userId,
                               @RequestBody final EventCreateRequestDto eventCreateRequestDto) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/{eventId}")
    public EventFullDto update(@PathVariable final long userId,
                               @PathVariable final long eventId,
                               @RequestBody final EventCreateRequestDto eventCreateRequestDto) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{eventId}")
    public List<EventShortDto> get(@PathVariable final long userId,
                                   @PathVariable final long eventId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<EventShortDto> getList(@PathVariable final long userId,
                                       @RequestParam(defaultValue = "0") @PositiveOrZero final long from,
                                       @RequestParam(defaultValue = "10") @Positive final long size) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/{eventId}/requests")
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
