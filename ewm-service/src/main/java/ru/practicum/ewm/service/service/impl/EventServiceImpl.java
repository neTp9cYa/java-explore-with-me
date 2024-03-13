package ru.practicum.ewm.service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.dto.event.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventParticipationRequestUpdateRequestDto;
import ru.practicum.ewm.service.dto.event.EventParticipationRequestUpdateResponseDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.dto.event.EventUpdateAdminRequestDto;
import ru.practicum.ewm.service.dto.event.EventUpdateRequestDto;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestDto;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.mapper.api.EventMapper;
import ru.practicum.ewm.service.mapper.api.ParticipationRequestMapper;
import ru.practicum.ewm.service.model.Category;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.model.EventState;
import ru.practicum.ewm.service.model.ParticipationRequest;
import ru.practicum.ewm.service.model.ParticipationRequestStatus;
import ru.practicum.ewm.service.model.User;
import ru.practicum.ewm.service.repository.CategoryRepository;
import ru.practicum.ewm.service.repository.EventRepository;
import ru.practicum.ewm.service.repository.ParticipationRequestRepository;
import ru.practicum.ewm.service.repository.UserRepository;
import ru.practicum.ewm.service.repository.specification.EventSpecification;
import ru.practicum.ewm.service.service.api.EventService;
import ru.practicum.ewm.service.service.request.GetEventsAdminRequest;
import ru.practicum.ewm.service.service.request.GetEventsPrivateRequest;
import ru.practicum.ewm.service.service.request.GetEventsPublicRequest;
import ru.practicum.stats.client.StatsClient;
import ru.practicum.utils.pagination.FlexPageRequest;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final ParticipationRequestRepository participationRequestRepository;

    private final ParticipationRequestMapper participationRequestMapper;

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
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

        if (eventDto.getPaid() != null) {
            updatingEvent.setPaid(eventDto.getPaid());
        }

        if (eventDto.getParticipantLimit() != null) {
            updatingEvent.setParticipantLimit(eventDto.getParticipantLimit());
        }

        if (eventDto.getRequestModeration() != null) {
            updatingEvent.setRequestModeration(eventDto.getRequestModeration());
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
    @Transactional(readOnly = true)
    public List<EventFullDto> getEvents(final GetEventsAdminRequest getEventsAdminRequest) {
        Specification<Event> specification = Specification
            .where(EventSpecification.users(getEventsAdminRequest.getUsers()))
            .and(EventSpecification.states(getEventsAdminRequest.getStates()))
            .and(EventSpecification.categories(getEventsAdminRequest.getCategories()))
            .and(EventSpecification.rangeStart(getEventsAdminRequest.getRangeStart()))
            .and(EventSpecification.rangeEnd(getEventsAdminRequest.getRangeEnd()));

        final Pageable pageable = FlexPageRequest.of(getEventsAdminRequest.getFrom(), getEventsAdminRequest.getSize());

        final Page<Event> eventPage = eventRepository.findAll(pageable);

        final List<EventFullDto> eventDtos = new ArrayList<>();
        final List<Long> eventIds = new ArrayList<>();
        eventPage.stream().forEach(event -> {
            eventDtos.add(eventMapper.toEventFullDto(event));
            eventIds.add(event.getId());
        });
        final Map<Long, Long> confirmedRequestCountForEvents = getCountForEventsByStatus(
            eventIds,
            ParticipationRequestStatus.CONFIRMED);
        eventDtos.stream().forEach(eventDto -> eventDto.setConfirmedRequests(
                confirmedRequestCountForEvents.getOrDefault(eventDto.getId(), 0L)));

        return eventDtos;
    }

    private Map<Long, Long> getCountForEventsByStatus(final List<Long> eventIds,
                                                      final ParticipationRequestStatus status) {
        return participationRequestRepository.getCountForEventsByStatus(eventIds, status)
            .collect(Collectors.toMap(
                countForEvent -> countForEvent.getEventId(),
                countForEvent -> countForEvent.getCount()));
    }

    @Override
    public List<EventShortDto> getEvents(final GetEventsPublicRequest getEventsPublicRequest) {
        Specification<Event> specification = Specification
            .where(EventSpecification.text(getEventsPublicRequest.getText()))
            .and(EventSpecification.categories(getEventsPublicRequest.getCategories()))
            .and(EventSpecification.paid(getEventsPublicRequest.getPaid()))
            .and(EventSpecification.rangeStart(getEventsPublicRequest.getRangeStart()))
            .and(EventSpecification.rangeEnd(getEventsPublicRequest.getRangeEnd()));
        //.and(EventSpecification.onlyAvailable(getEventsPublicRequest.isOnlyAvailable()))

        final Pageable pageable = FlexPageRequest.of(
            getEventsPublicRequest.getFrom(),
            getEventsPublicRequest.getSize());

        // sort

        final Page<Event> eventPage = eventRepository.findAll(specification, pageable);

        return eventPage.stream().map(event -> eventMapper.toEventShortDto(event)).collect(Collectors.toList());
    }

    @Override
    public List<EventShortDto> getEvents(final long userId, final GetEventsPrivateRequest getEventsPrivateRequest) {
        Specification<Event> specification = Specification
            .where(EventSpecification.user(userId));

        final Pageable pageable = FlexPageRequest.of(
            getEventsPrivateRequest.getFrom(),
            getEventsPrivateRequest.getSize());

        final Page<Event> eventPage = eventRepository.findAll(specification, pageable);

        return eventPage.stream().map(event -> eventMapper.toEventShortDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventFullDto getPublicEvent(final long eventId) {
        final Event event = eventRepository.findByIdAndState(eventId, EventState.PUBLISHED)
            .orElseThrow(() -> new NotFoundException(String.format("Event with id %d not found", eventId)));

        return eventMapper.toEventFullDto(event);
    }

    @Override
    public EventFullDto getPublicEvent(final long userId, final long eventId) {
        final Event event = eventRepository.findByIdAndUser_Id(eventId, userId)
            .orElseThrow(() -> new NotFoundException(String.format("Event with id %d not found", eventId)));

        return eventMapper.toEventFullDto(event);
    }

    @Override
    public EventFullDto create(long userId, final EventCreateRequestDto eventCreateRequestDto) {

        final User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        final Category category = categoryRepository.findById(eventCreateRequestDto.getCategory())
            .orElseThrow(() -> new NotFoundException(
                String.format("Category with id %d not found", eventCreateRequestDto.getCategory())));

        final Event creatingEvent = eventMapper.toEvent(eventCreateRequestDto, user, category);
        final Event createdEvent = eventRepository.save(creatingEvent);

        return eventMapper.toEventFullDto(createdEvent);
    }

    @Override
    public EventFullDto update(final long userId,
                               final long eventId,
                               final EventUpdateRequestDto eventDto) {
        final Event updatingEvent = eventRepository.findByIdAndUser_Id(eventId, userId)
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

        if (eventDto.getPaid() != null) {
            updatingEvent.setPaid(eventDto.getPaid());
        }

        if (eventDto.getParticipantLimit() != null) {
            updatingEvent.setParticipantLimit(eventDto.getParticipantLimit());
        }

        if (eventDto.getRequestModeration() != null) {
            updatingEvent.setRequestModeration(eventDto.getRequestModeration());
        }

        if (eventDto.getStateAction() != null) {
            switch (eventDto.getStateAction()) {
                case SEND_TO_REVIEW:
                    updatingEvent.setState(EventState.PENDING);
                    break;
                case CANCEL_REVIEW:
                    updatingEvent.setState(EventState.CANCELED);
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
    public EventParticipationRequestUpdateResponseDto updateRequests(
        final long userId,
        final long eventId,
        final EventParticipationRequestUpdateRequestDto eventParticipationRequestUpdateRequestDto) {

        final User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        final Event event = eventRepository.findByIdAndUser_Id(eventId, userId)
            .orElseThrow(() -> new NotFoundException(String.format("Event with id %d not found", eventId)));

        final List<ParticipationRequest> participationRequests = participationRequestRepository
            .findAllById(eventParticipationRequestUpdateRequestDto.getRequestIds());
        for (final ParticipationRequest participationRequest : participationRequests) {
            if (participationRequest.getEvent().getId() != eventId ||
                participationRequest.getStatus() != ParticipationRequestStatus.PENDING) {
                throw new IllegalArgumentException();
            }
        }

        final List<ParticipationRequest> confirmingParticipationRequests = new ArrayList<>();
        final List<ParticipationRequest> rejectingParticipationRequests = new ArrayList<>();

        if (event.getParticipantLimit() == 0) {
            confirmingParticipationRequests.addAll(participationRequests);
        } else {
            final long confirmedRequestCount =
                participationRequestRepository.countByEvent_IdAndStatus(eventId, ParticipationRequestStatus.CONFIRMED);
            final long availableRequestCount = event.getParticipantLimit() - confirmedRequestCount;
            confirmingParticipationRequests.addAll(participationRequests.stream()
                .limit(availableRequestCount)
                .collect(Collectors.toList()));
            rejectingParticipationRequests.addAll(participationRequests.stream()
                .skip(availableRequestCount)
                .collect(Collectors.toList()));
        }

        confirmingParticipationRequests.forEach(request -> request.setStatus(ParticipationRequestStatus.CONFIRMED));
        final List<ParticipationRequest> confirmedParticipationRequests = participationRequestRepository
            .saveAll(confirmingParticipationRequests);

        return eventMapper.toDto(confirmedParticipationRequests, rejectingParticipationRequests);
    }

    @Override
    public List<ParticipationRequestDto> getRequests(final long userId, final long eventId) {
        final List<ParticipationRequest> participationRequests = participationRequestRepository
            .findAllByUser_IdAndEvent_Id(userId, eventId);

        return participationRequests.stream()
            .map(participationRequest -> participationRequestMapper.toParticipationRequestDto(participationRequest))
            .collect(Collectors.toList());
    }


}
