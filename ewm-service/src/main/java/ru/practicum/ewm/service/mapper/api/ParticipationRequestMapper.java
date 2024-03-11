package ru.practicum.ewm.service.mapper.api;

import ru.practicum.ewm.service.dto.participant.ParticipationRequestDto;
import ru.practicum.ewm.service.model.ParticipationRequest;

public interface ParticipationRequestMapper {
    ParticipationRequestDto toParticipationRequestDto(final ParticipationRequest participationRequest);
}
