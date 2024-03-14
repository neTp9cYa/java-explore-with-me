package ru.practicum.ewm.service.controller.privateArea;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.event.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventParticipationRequestUpdateRequestDto;
import ru.practicum.ewm.service.dto.event.EventParticipationRequestUpdateResponseDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.dto.event.EventUpdateRequestDto;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestDto;
import ru.practicum.ewm.service.service.api.EventService;
import ru.practicum.ewm.service.service.request.GetEventsPrivateRequest;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
@Validated
public class EventPrivateController {

    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LogInputOutputAnnotaion
    public EventFullDto create(@PathVariable final long userId,
                               @RequestBody @Valid final EventCreateRequestDto eventCreateRequestDto) {
        return eventService.create(userId, eventCreateRequestDto);
    }

    @PatchMapping("/{eventId}")
    @LogInputOutputAnnotaion
    public EventFullDto update(@PathVariable final long userId,
                               @PathVariable final long eventId,
                               @RequestBody @Valid final EventUpdateRequestDto eventUpdateRequestDto) {
        return eventService.update(userId, eventId, eventUpdateRequestDto);
    }

    @GetMapping("/{eventId}")
    @LogInputOutputAnnotaion
    public EventFullDto getEvent(@PathVariable final long userId,
                                 @PathVariable final long eventId) {
        return eventService.getPublicEvent(userId, eventId);
    }

    @GetMapping
    @LogInputOutputAnnotaion
    public List<EventShortDto> getEvents(@PathVariable final long userId,
                                         @RequestParam(defaultValue = "0") @PositiveOrZero final long from,
                                         @RequestParam(defaultValue = "10") @Positive final int size) {
        final GetEventsPrivateRequest getEventsPrivateRequest = GetEventsPrivateRequest.builder()
            .from(from)
            .size(size)
            .build();

        return eventService.getEvents(userId, getEventsPrivateRequest);
    }

    @PatchMapping("/{eventId}/requests")
    @LogInputOutputAnnotaion
    public EventParticipationRequestUpdateResponseDto updateRequests(
        @PathVariable final long userId,
        @PathVariable final long eventId,
        @RequestBody @Valid final EventParticipationRequestUpdateRequestDto eventParticipationRequestUpdateRequestDto) {

        return eventService.updateRequests(userId, eventId, eventParticipationRequestUpdateRequestDto);
    }

    @GetMapping("/{eventId}/requests")
    @LogInputOutputAnnotaion
    public List<ParticipationRequestDto> getRequests(@PathVariable final long userId,
                                                     @PathVariable final long eventId) {
        return eventService.getRequests(userId, eventId);
    }
}
