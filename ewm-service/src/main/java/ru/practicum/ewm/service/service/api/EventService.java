package ru.practicum.ewm.service.service.api;

import java.util.List;
import ru.practicum.ewm.service.dto.event.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.event.EventUpdateAdminRequestDto;
import ru.practicum.ewm.service.dto.event.EventUpdateRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestViewDto;
import ru.practicum.ewm.service.service.request.GetEventsAdminRequest;
import ru.practicum.ewm.service.service.request.GetEventsPublicRequest;

public interface EventService {
    EventFullDto updateByAdmin(final long eventId, final EventUpdateAdminRequestDto eventDto);
    List<EventFullDto> getEvents(final GetEventsAdminRequest getEventsPublicRequest);
    List<EventShortDto> getEvents(final GetEventsPublicRequest getEventsPublicRequest);

    EventFullDto getPublishedById(final long id);

    EventFullDto addEvent(EventCreateRequestDto event);

    List<ParticipationRequestViewDto> findUserEventParticipationRequests(final long initiatorId, final long eventId);

    EventFullDto updateEventByInitiator(final long initiatorId,
                                        final long eventId,
                                        final EventUpdateRequestDto eventUpdateRequestDto);

    List<EventShortDto> getUserEvents(final long userId, final long from, final long size);

    EventFullDto getUserEventById(final long userId, final long eventId);

}
