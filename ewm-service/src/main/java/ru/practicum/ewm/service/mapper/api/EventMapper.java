package ru.practicum.ewm.service.mapper.api;

import java.util.List;
import ru.practicum.ewm.service.dto.event.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.model.Event;

public interface EventMapper {
    EventFullDto toEventFullDto(final Event event);
    EventShortDto toEventShortDto(final Event event);
    Event toEvent(final EventCreateRequestDto eventDto);
}
