package ru.practicum.ewm.service.service.api;

import java.util.List;
import ru.practicum.ewm.service.dto.event.EventCreateRequestDto;
import ru.practicum.ewm.service.dto.event.EventParticipationRequestUpdateRequestDto;
import ru.practicum.ewm.service.dto.event.EventParticipationRequestUpdateResponseDto;
import ru.practicum.ewm.service.dto.event.EventUpdateAdminRequestDto;
import ru.practicum.ewm.service.dto.event.EventUpdateRequestDto;
import ru.practicum.ewm.service.dto.event.EventFullDto;
import ru.practicum.ewm.service.dto.event.EventShortDto;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestDto;
import ru.practicum.ewm.service.service.request.GetEventsAdminRequest;
import ru.practicum.ewm.service.service.request.GetEventsPrivateRequest;
import ru.practicum.ewm.service.service.request.GetEventsPublicRequest;

public interface EventService {
    EventFullDto updateByAdmin(final long eventId, final EventUpdateAdminRequestDto eventDto);
    List<EventFullDto> getEvents(final GetEventsAdminRequest getEventsAdminRequest);
    List<EventShortDto> getEvents(final GetEventsPublicRequest getEventsPublicRequest);
    List<EventShortDto> getEvents(final long userId, final GetEventsPrivateRequest getEventsPrivateRequest);
    EventFullDto getPublicEvent(final long eventId);
    EventFullDto getPublicEvent(final long userId, final long eventId);
    EventFullDto create(final long userId, final EventCreateRequestDto eventCreateRequestDto);
    EventFullDto update(final long userId,
                        final long eventId,
                        final EventUpdateRequestDto eventUpdateRequestDto);
    EventParticipationRequestUpdateResponseDto updateRequests(
        final long userId,
        final long eventId,
        final EventParticipationRequestUpdateRequestDto eventParticipationRequestUpdateRequestDto);

    List<ParticipationRequestDto> getRequests(final long userId, final long eventId);

}
