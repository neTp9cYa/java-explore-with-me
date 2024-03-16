package ru.practicum.ewm.service.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.dto.event.EventBaseDto;
import ru.practicum.ewm.service.dto.event.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventParticipationRequestUpdateRequestDto;
import ru.practicum.ewm.service.dto.event.EventParticipationRequestUpdateResponseDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.dto.event.EventSort;
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
import ru.practicum.stats.client.client.StatsClient;
import ru.practicum.stats.client.request.GetStatsRequest;
import ru.practicum.stats.dto.StatItemDto;
import ru.practicum.utils.pagination.FlexPageRequest;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private static final String EVENT_URL_FORMAT = "/events/%s";
    private static final LocalDateTime STAT_MIN_DATE_TIME = LocalDateTime.of(1, 1, 1, 0, 0, 0);
    private static final LocalDateTime STAT_MAX_DATE_TIME = LocalDateTime.of(9999, 1, 1, 0, 0, 0);

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final ParticipationRequestRepository participationRequestRepository;

    private final ParticipationRequestMapper participationRequestMapper;

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final StatsClient statsClient;

    @Override
    @Transactional
    public EventFullDto createByUser(long userId, final EventCreateRequestDto eventCreateRequestDto) {
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
    @Transactional
    public EventFullDto updateByUser(final long userId,
                                     final long eventId,
                                     final EventUpdateRequestDto eventDto) {
        final Event updatingEvent = eventRepository.findByIdAndUser_Id(eventId, userId)
            .orElseThrow(() -> new NotFoundException(String.format("Event with id %d not found", eventId)));

        if (updatingEvent.getState() != EventState.PENDING && updatingEvent.getState() != EventState.REJECTED) {
            throw new IllegalStateException();
        }

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

        if (eventDto.getLocation() != null) {
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
            updatingEvent.setRequestModeration(eventDto.getRequestModeration());
        }

        if (eventDto.getStateAction() != null) {
            final EventState newEventState = eventDto.getStateAction().toEventState();
            updatingEvent.setState(newEventState);
        }

        if (eventDto.getTitle() != null) {
            updatingEvent.setTitle(eventDto.getTitle());
        }

        final Event updatedEvent = eventRepository.save(updatingEvent);

        return toDto(updatedEvent);
    }

    @Override
    @Transactional
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

        if (eventDto.getLocation() != null) {
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
            updatingEvent.setRequestModeration(eventDto.getRequestModeration());
        }

        if (eventDto.getStateAction() != null) {
            final EventState newEventState = eventDto.getStateAction().toEventState();
            if (newEventState == EventState.PUBLISHED) {
                if (updatingEvent.getState() != EventState.PENDING) {
                    throw new IllegalStateException();
                }
                updatingEvent.setPublishedOn(LocalDateTime.now());
            } else if (newEventState == EventState.REJECTED) {
                if (updatingEvent.getState() != EventState.PENDING) {
                    throw new IllegalStateException();
                }
            }
            updatingEvent.setState(newEventState);
        }

        if (eventDto.getTitle() != null) {
            updatingEvent.setTitle(eventDto.getTitle());
        }

        final Event updatedEvent = eventRepository.save(updatingEvent);

        return toDto(updatedEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public EventFullDto getPublicEvent(final long eventId) {
        final Event event = eventRepository.findByIdAndState(eventId, EventState.PUBLISHED)
            .orElseThrow(() -> new NotFoundException(String.format("Event with id %d not found", eventId)));

        return toDto(event);
    }

    @Override
    @Transactional(readOnly = true)
    public EventFullDto getOwnEvent(final long userId, final long eventId) {
        final Event event = eventRepository.findByIdAndUser_Id(eventId, userId)
            .orElseThrow(() -> new NotFoundException(String.format("Event with id %d not found", eventId)));

        return toDto(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventFullDto> getEvents(final GetEventsAdminRequest getEventsAdminRequest) {
        final Specification<Event> specification = Specification
            .where(EventSpecification.users(getEventsAdminRequest.getUsers()))
            .and(EventSpecification.states(getEventsAdminRequest.getStates()))
            .and(EventSpecification.categories(getEventsAdminRequest.getCategories()))
            .and(EventSpecification.rangeStart(getEventsAdminRequest.getRangeStart()))
            .and(EventSpecification.rangeEnd(getEventsAdminRequest.getRangeEnd()));

        final Pageable pageable = FlexPageRequest.of(getEventsAdminRequest.getFrom(), getEventsAdminRequest.getSize());

        final Page<Event> eventPage = eventRepository.findAll(specification, pageable);

        return toDtos(eventPage.stream(), (event) -> eventMapper.toEventFullDto((event)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventShortDto> getEvents(final GetEventsPublicRequest getEventsPublicRequest) {
        final Specification<Event> specification = Specification
            .where(EventSpecification.text(getEventsPublicRequest.getText()))
            .and(EventSpecification.categories(getEventsPublicRequest.getCategories()))
            .and(EventSpecification.paid(getEventsPublicRequest.getPaid()))
            .and(EventSpecification.rangeStart(getEventsPublicRequest.getRangeStart()))
            .and(EventSpecification.rangeEnd(getEventsPublicRequest.getRangeEnd()))
            .and(EventSpecification.onlyAvaliable(getEventsPublicRequest.isOnlyAvailable()));

        final Pageable pageable = FlexPageRequest.of(
            getEventsPublicRequest.getFrom(),
            getEventsPublicRequest.getSize(),
            Sort.by(getEventsPublicRequest.getSort().toSortProperty()));

        final Page<Event> eventPage = eventRepository.findAll(specification, pageable);

        final List<EventShortDto> eventDtos = toDtos(
            eventPage.stream(),
            (event) -> eventMapper.toEventShortDto((event)));

        if (getEventsPublicRequest.getSort() == EventSort.VIEWS) {
            eventDtos.sort((e1, e2) -> (int) (e1.getViews() - e2.getViews()));
        }

        return eventDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventShortDto> getEvents(final long userId, final GetEventsPrivateRequest getEventsPrivateRequest) {
        final Specification<Event> specification = Specification
            .where(EventSpecification.user(userId));

        final Pageable pageable = FlexPageRequest.of(
            getEventsPrivateRequest.getFrom(),
            getEventsPrivateRequest.getSize());

        final Page<Event> eventPage = eventRepository.findAll(specification, pageable);

        return toDtos(eventPage.stream(), event -> eventMapper.toEventShortDto(event));
    }

    @Override
    @Transactional
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
                throw new IllegalStateException();
            }
        }

        final List<ParticipationRequest> confirmingParticipationRequests = new ArrayList<>();
        final List<ParticipationRequest> rejectingParticipationRequests = new ArrayList<>();

        switch (eventParticipationRequestUpdateRequestDto.getStatus()) {
            case CONFIRMED:
                if (event.getParticipantLimit() == 0) {
                    confirmingParticipationRequests.addAll(participationRequests);
                } else {
                    final long confirmedRequestCount =
                        participationRequestRepository.countByEvent_IdAndStatus(
                            eventId,
                            ParticipationRequestStatus.CONFIRMED);
                    if (event.getParticipantLimit() <= confirmedRequestCount) {
                        throw new IllegalStateException();
                    }
                    final long availableRequestCount = event.getParticipantLimit() - confirmedRequestCount;
                    confirmingParticipationRequests.addAll(participationRequests.stream()
                        .limit(availableRequestCount)
                        .collect(Collectors.toList()));
                    rejectingParticipationRequests.addAll(participationRequests.stream()
                        .skip(availableRequestCount)
                        .collect(Collectors.toList()));
                }
                break;
            case REJECTED:
                rejectingParticipationRequests.addAll(participationRequests);
                break;
            default:
                throw new IllegalArgumentException();
        }

        confirmingParticipationRequests.forEach(request -> request.setStatus(ParticipationRequestStatus.CONFIRMED));
        final List<ParticipationRequest> confirmedParticipationRequests = participationRequestRepository
            .saveAll(confirmingParticipationRequests);

        rejectingParticipationRequests.forEach(request -> request.setStatus(ParticipationRequestStatus.REJECTED));
        final List<ParticipationRequest> rejectedParticipationRequests = participationRequestRepository
            .saveAll(rejectingParticipationRequests);

        return eventMapper.toDto(confirmedParticipationRequests, rejectedParticipationRequests);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParticipationRequestDto> getRequests(final long userId, final long eventId) {
        final List<ParticipationRequest> participationRequests = participationRequestRepository
            .findAllByEvent_User_IdAndEvent_Id(userId, eventId);

        return participationRequests.stream()
            .map(participationRequest -> participationRequestMapper.toParticipationRequestDto(participationRequest))
            .collect(Collectors.toList());
    }

    private <EventDtoT extends EventBaseDto> List<EventDtoT> toDtos(
        final Stream<Event> events,
        final Function<Event, EventDtoT> mapToDto) {

        final List<EventDtoT> eventDtos = new ArrayList<>();
        final Map<Long, EventDtoT> eventDtosById = new HashMap<>();
        final Map<String, EventDtoT> eventDtosByUrl = new HashMap<>();
        final List<Long> eventIds = new ArrayList<>();
        final List<String> eventUrls = new ArrayList<>();

        events.forEach(event -> {
            final EventDtoT eventDto = mapToDto.apply(event);
            final String eventUrl = String.format(EVENT_URL_FORMAT, eventDto.getId());
            eventDtos.add(eventDto);
            eventDtosById.put(eventDto.getId(), eventDto);
            eventDtosByUrl.put(eventUrl, eventDto);
            eventIds.add(eventDto.getId());
            eventUrls.add(eventUrl);

            enrichWithConfirmedReqeusts(eventDtosById, eventIds);
            enrichWithViews(eventDtosByUrl, eventUrls);

        });

        return eventDtos;
    }

    private EventFullDto toDto(final Event event) {
        final EventFullDto eventDto = eventMapper.toEventFullDto(event);
        final String eventUrl = String.format(EVENT_URL_FORMAT, eventDto.getId());

        enrichWithConfirmedReqeusts(eventDto);
        enrichWithViews(eventDto, eventUrl);

        return eventDto;
    }

    private void enrichWithConfirmedReqeusts(final Map<Long, ? extends EventBaseDto> eventDtosById,
                                             final List<Long> eventIds) {

        participationRequestRepository
            .getCountForEventsByStatus(eventIds, ParticipationRequestStatus.CONFIRMED)
            .forEach(participationRequestCount ->
                eventDtosById
                    .get(participationRequestCount.getEventId())
                    .setConfirmedRequests(participationRequestCount.getCount()));
    }

    private void enrichWithConfirmedReqeusts(final EventBaseDto eventDto) {
        participationRequestRepository
            .getCountForEventsByStatus(List.of(eventDto.getId()), ParticipationRequestStatus.CONFIRMED)
            .findFirst()
            .ifPresent(participationRequestCount -> eventDto
                .setConfirmedRequests(participationRequestCount.getCount()));
    }

    private void enrichWithViews(final Map<String, ? extends EventBaseDto> eventDtosByUrls,
                                 final List<String> eventUrls) {
        final GetStatsRequest getStatsRequest = new GetStatsRequest(STAT_MIN_DATE_TIME, STAT_MAX_DATE_TIME);
        getStatsRequest.setUris(eventUrls);
        getStatsRequest.setUnique(true);

        statsClient.getStats(getStatsRequest)
            .stream()
            .forEach(statItem -> {
                eventDtosByUrls
                    .get(statItem.getUri())
                    .setViews(statItem.getHits());
            });
    }

    private void enrichWithViews(final EventBaseDto eventDto, final String eventUrl) {
        final GetStatsRequest getStatsRequest = new GetStatsRequest(STAT_MIN_DATE_TIME, STAT_MAX_DATE_TIME);
        getStatsRequest.setUris(List.of(eventUrl));
        getStatsRequest.setUnique(true);

        final List<StatItemDto> viewCountForEvent = statsClient.getStats(getStatsRequest);
        if (viewCountForEvent.size() == 1) {
            eventDto.setViews(viewCountForEvent.get(0).getHits());
        }
    }
}
