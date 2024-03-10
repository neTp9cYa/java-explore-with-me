package ru.practicum.ewm.service.mapper.api;

import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.model.Event;

public interface EventMapper {
    EventFullDto toEventFullDto(final Event event);
}
