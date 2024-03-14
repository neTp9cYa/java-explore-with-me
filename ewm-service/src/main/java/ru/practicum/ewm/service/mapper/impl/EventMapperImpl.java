package ru.practicum.ewm.service.mapper.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.service.dto.Location.LocationDto;
import ru.practicum.ewm.service.dto.event.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventParticipationRequestUpdateResponseDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.mapper.api.CategoryMapper;
import ru.practicum.ewm.service.mapper.api.EventMapper;
import ru.practicum.ewm.service.mapper.api.ParticipationRequestMapper;
import ru.practicum.ewm.service.mapper.api.UserMapper;
import ru.practicum.ewm.service.model.Category;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.model.EventState;
import ru.practicum.ewm.service.model.ParticipationRequest;
import ru.practicum.ewm.service.model.User;

@Component
@RequiredArgsConstructor
public class EventMapperImpl implements EventMapper {

    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;
    private final ParticipationRequestMapper participationRequestMapper;

    @Override
    public EventFullDto toEventFullDto(Event event) {
        return EventFullDto.builder()
            .annotation(event.getAnnotation())
            .category(categoryMapper.toCategoryDto(event.getCategory()))
            .createdOn(event.getCreatedOn())
            .description(event.getDescription())
            .eventDate(event.getEventDate())
            .id(event.getId())
            .initiator(userMapper.toUserShortDto(event.getUser()))
            .location(new LocationDto(event.getLatitude(), event.getLongitude()))
            .paid(event.isPaid())
            .participantLimit(event.getParticipantLimit())
            .publishedOn(event.getPublishedOn())
            .requestModeration(event.isRequestModeration())
            .state(event.getState())
            .title(event.getTitle())
            //.confirmedRequests()
            //.views()
            .build();
    }

    @Override
    public EventShortDto toEventShortDto(final Event event) {

        return EventShortDto.builder()
            .annotation(event.getAnnotation())
            .category(categoryMapper.toCategoryDto(event.getCategory()))
            .eventDate(event.getEventDate())
            .id(event.getId())
            .initiator(userMapper.toUserShortDto(event.getUser()))
            .paid(event.isPaid())
            .title(event.getTitle())
            //.confirmedRequests()
            //.views()
            .build();
    }

    @Override
    public Event toEvent(final EventCreateRequestDto eventDto,
                         final User user,
                         final Category category) {
        return Event.builder()
            .title(eventDto.getTitle())
            .annotation(eventDto.getAnnotation())
            .description(eventDto.getDescription())
            .category(category)
            .participantLimit(eventDto.getParticipantLimit())
            .state(EventState.PENDING)
            .paid(eventDto.getPaid())
            .eventDate(eventDto.getEventDate())
            .createdOn(LocalDateTime.now())
            .publishedOn(null)
            .user(user)
            .requestModeration(eventDto.getRequestModeration())
            .build();
    }

    @Override
    public EventParticipationRequestUpdateResponseDto toDto(List<ParticipationRequest> confirmedRequest,
                                                            List<ParticipationRequest> rejectedRequest) {
        return EventParticipationRequestUpdateResponseDto.builder()
            .confirmedRequests(participationRequestMapper.toParticipationRequestDtos(confirmedRequest))
            .rejectedRequests(participationRequestMapper.toParticipationRequestDtos(rejectedRequest))
            .build();
    }
}
