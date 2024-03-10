package ru.practicum.ewm.service.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.service.dto.event.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.event.EventUpdateAdminRequestDto;
import ru.practicum.ewm.service.dto.event.EventUpdateRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestViewDto;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.mapper.api.EventMapper;
import ru.practicum.ewm.service.model.Category;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.model.EventState;
import ru.practicum.ewm.service.repository.CategoryRepository;
import ru.practicum.ewm.service.repository.EventRepository;
import ru.practicum.ewm.service.repository.ParticipationRequestRepository;
import ru.practicum.ewm.service.repository.UserRepository;
import ru.practicum.ewm.service.service.api.EventService;
import ru.practicum.ewm.service.service.request.GetEventsAdminRequest;
import ru.practicum.ewm.service.service.request.GetEventsPublicRequest;
import ru.practicum.stats.client.StatsClient;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ParticipationRequestRepository participationRequestRepository;
    private final StatsClient statsClient;

    @Override
    public EventFullDto updateByAdmin(final long eventId, final EventUpdateAdminRequestDto eventDto) {
        final Event updatingEvent = eventRepository.findById(eventId)
            .orElseThrow(() -> new NotFoundException(String.format("Event with id %d not found", eventId)));

        if (eventDto.getAnnotation() != null) {
            updatingEvent.setAnnotation(eventDto.getAnnotation());
        }

        if (eventDto.getCategory() != null) {
            final Category category = categoryRepository.findById(eventDto.getCategory())
                .orElseThrow(() -> new NotFoundException(
                    String.format("Category with id %d not found", eventDto.getCategory())));
            updatingEvent.setCategory(category);
        }

        if (eventDto.getDescription() != null) {
            updatingEvent.setDescription(eventDto.getDescription());
        }

        if (eventDto.getEventDate() != null) {
            updatingEvent.setEventDate(eventDto.getEventDate());
        }

        if( eventDto.getLocation() != null) {
            if (eventDto.getLocation().getLat() != null) {
                updatingEvent.setLatitude(eventDto.getLocation().getLat());
            }
            if (eventDto.getLocation().getLon() != null) {
                updatingEvent.setLongitude(eventDto.getLocation().getLon());
            }
        }

        if (eventDto.getPaid() != null) {
            updatingEvent.setPaid(eventDto.getPaid());
        }

        if (eventDto.getParticipantLimit() != null) {
            updatingEvent.setParticipantLimit(eventDto.getParticipantLimit());
        }

        if (eventDto.getRequestModeration() != null) {
            updatingEvent.setRequiredModeration(eventDto.getRequestModeration());
        }

        if (eventDto.getStateAction() != null) {
            switch (eventDto.getStateAction()) {
                case PUBLISH_EVENT:
                    updatingEvent.setState(EventState.PUBLISHED);
                    break;
                case REJECT_EVENT:
                    updatingEvent.setState(EventState.REJECTED);
                    break;
                default:
                    throw new IllegalArgumentException("State action is not supported");
            }
        }

        if (eventDto.getTitle() != null) {
            updatingEvent.setTitle(eventDto.getTitle());
        }

        final Event updatedEvent = eventRepository.save(updatingEvent);

        return eventMapper.toEventFullDto(updatedEvent);
    }

    @Override
    public List<EventFullDto> getEvents(final GetEventsAdminRequest getEventsPublicRequest) {
        return null;
    }

    @Override
    public List<EventShortDto> getEvents(GetEventsPublicRequest getEventsPublicRequest) {
        return null;
    }

    @Override
    public EventFullDto getPublishedById(long id) {
        return null;
    }

    @Override
    public EventFullDto addEvent(EventCreateRequestDto event) {
        return null;
    }

    @Override
    public List<ParticipationRequestViewDto> findUserEventParticipationRequests(long initiatorId,
                                                                                long eventId) {
        return null;
    }

    @Override
    public EventFullDto updateEventByInitiator(long initiatorId, long eventId,
                                               EventUpdateRequestDto eventUpdateRequestDto) {
        return null;
    }

    @Override
    public List<EventShortDto> getUserEvents(long userId, long from, long size) {
        return null;
    }

    @Override
    public EventFullDto getUserEventById(long userId, long eventId) {
        return null;
    }
}
