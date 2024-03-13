package ru.practicum.ewm.service.controller.adminArea;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.event.EventUpdateAdminRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.model.EventState;
import ru.practicum.ewm.service.service.api.EventService;
import ru.practicum.ewm.service.service.request.GetEventsAdminRequest;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class EventAdminController {

    private final EventService eventService;

    @PatchMapping("/{eventId}")
    @LogInputOutputAnnotaion
    public EventFullDto update(@PathVariable final int eventId,
                               @RequestBody @Valid final EventUpdateAdminRequestDto eventDto) {
        return eventService.updateByAdmin(eventId, eventDto);
    }

    @GetMapping
    @LogInputOutputAnnotaion
    public List<EventFullDto> getEvents(@RequestParam final List<Long> users,
                                        @RequestParam final List<EventState> states,
                                        @RequestParam final List<Long> categories,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final LocalDateTime rangeStart,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") final LocalDateTime rangeEnd,
                                        @RequestParam(defaultValue = "0") @PositiveOrZero final long from,
                                        @RequestParam(defaultValue = "10") @Positive final int size) {
        final GetEventsAdminRequest getEventsAdminRequest = GetEventsAdminRequest.builder()
            .users(users)
            .states(states)
            .categories(categories)
            .rangeStart(rangeStart)
            .rangeEnd(rangeEnd)
            .from(from)
            .size(size)
            .build();

        return eventService.getEvents(getEventsAdminRequest);
    }

}
