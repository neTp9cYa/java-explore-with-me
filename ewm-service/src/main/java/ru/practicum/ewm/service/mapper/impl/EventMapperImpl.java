package ru.practicum.ewm.service.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.service.dto.event.EventFullDto;
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
            .requestModeration(event.isRequiredModeration())
            //.state(event.getState())
            .title(event.getTitle())
            //.views()
            .build();
    }
}
