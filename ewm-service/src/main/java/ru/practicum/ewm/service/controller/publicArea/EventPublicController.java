package ru.practicum.ewm.service.controller.publicArea;

import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.dto.event.EventSort;
import ru.practicum.ewm.service.model.EventState;
import ru.practicum.ewm.service.service.api.EventService;
import ru.practicum.ewm.service.service.api.StatService;
import ru.practicum.ewm.service.service.request.GetEventsPublicRequest;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
public class EventPublicController {

    private final EventService eventService;
    private final StatService statService;

    @GetMapping("/{eventId}")
    @LogInputOutputAnnotaion
    public EventFullDto getEvent(@PathVariable final long eventId) {
        return eventService.getPublicEvent(eventId);
    }

    @GetMapping
    @LogInputOutputAnnotaion
    public List<EventShortDto> getEvents(
        @RequestParam(required = false) final String text,
        @RequestParam(required = false) final List<Long> categories,
        @RequestParam(required = false) final Boolean paid,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final LocalDateTime rangeStart,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final LocalDateTime rangeEnd,
        @RequestParam(defaultValue = "false") final boolean onlyAvailable,
        @RequestParam(defaultValue = "EVENT_DATE") final EventSort sort,
        @RequestParam(defaultValue = "0") @PositiveOrZero final long from,
        @RequestParam(defaultValue = "10") @Positive final int size,
        final HttpServletRequest request) {

        if (rangeStart != null && rangeEnd != null && rangeStart.isAfter(rangeEnd)) {
            throw new ValidationException("rangeStart should be less or equal rangeEnd");
        }

        final GetEventsPublicRequest getEventsPublicRequest = GetEventsPublicRequest.builder()
            .state(EventState.PUBLISHED)
            .text(text)
            .categories(categories)
            .paid(paid)
            .rangeStart(rangeStart)
            .rangeEnd(rangeEnd)
            .onlyAvailable(onlyAvailable)
            .sort(sort)
            .from(from)
            .size(size)
            .build();

        final List<EventShortDto> events = eventService.getEvents(getEventsPublicRequest);

        statService.addHit(request);

        return events;
    }
}