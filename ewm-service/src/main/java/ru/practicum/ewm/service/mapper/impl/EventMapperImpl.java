package ru.practicum.ewm.service.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.service.dto.event.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.mapper.api.CategoryMapper;
import ru.practicum.ewm.service.mapper.api.EventMapper;
import ru.practicum.ewm.service.model.Event;

@Component
@RequiredArgsConstructor
public class EventMapperImpl implements EventMapper {

    private final CategoryMapper categoryMapper;

    @Override
    public EventFullDto toEventFullDto(Event event) {
        return EventFullDto.builder()
            .annotation(event.getAnnotation())
            .category(categoryMapper.toCategoryDto(event.getCategory()))
            //.confirmedRequests()
            .createdOn(event.getCreatedOn())
            .description(event.getDescription())
            .eventDate(event.getEventDate())
            .id(event.getId())
            //.initiator()
            //.location()
            .paid(event.isPaid())
            .participantLimit(event.getParticipantLimit())
            .publishedOn(event.getPublishedOn())
            .requestModeration(event.isRequestModeration())
            //.state(event.getState())
            .title(event.getTitle())
            //.views()
            .build();
    }

    @Override
    public EventShortDto toEventShortDto(final Event event) {

        return EventShortDto.builder()
            .annotation(event.getAnnotation())
            .category(categoryMapper.toCategoryDto(event.getCategory()))
            //.confirmedRequests()
            .eventDate(event.getEventDate())
            .id(event.getId())
            //.initiator()
            .paid(event.isPaid())
            .title(event.getTitle())
            //.views()
            .build();
    }

    @Override
    public Event toEvent(EventCreateRequestDto eventDto) {
        return Event.builder()
            .title(eventDto.getTitle())
            .annotation(eventDto.getAnnotation())
            .description(eventDto.getDescription())
            //.category(eventDto.getCategory())
            .participantLimit(eventDto.getParticipantLimit())
            //.state()
            .paid(eventDto.getPaid())
            .eventDate(eventDto.getEventDate())
            //.createdOn()
            //.publishedOn()
            //.user()
            .requestModeration(eventDto.getRequestModeration())
            .build();
    }
}
