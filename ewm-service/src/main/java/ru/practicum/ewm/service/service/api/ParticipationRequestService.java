package ru.practicum.ewm.service.service.api;

import java.util.List;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestDto;

public interface ParticipationRequestService {
    ParticipationRequestDto create(final long userId, final long eventId);
    ParticipationRequestDto cancel(final long userId, final long requestId);
    List<ParticipationRequestDto> getRequests(final long userId);
}
