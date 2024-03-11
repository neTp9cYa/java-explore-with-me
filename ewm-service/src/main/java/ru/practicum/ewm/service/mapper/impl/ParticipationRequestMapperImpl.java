package ru.practicum.ewm.service.mapper.impl;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestDto;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestStatus;
import ru.practicum.ewm.service.mapper.api.ParticipationRequestMapper;
import ru.practicum.ewm.service.model.ParticipationRequest;

@Component
public class ParticipationRequestMapperImpl implements ParticipationRequestMapper {

    @Override
    public ParticipationRequestDto toParticipationRequestDto(ParticipationRequest participationRequest) {
        return ParticipationRequestDto.builder()
            .id(participationRequest.getId())
            .created(participationRequest.getCreatedOn())
            //.status(participationRequest.getStatus())
            .event(participationRequest.getEvent().getId())
            .requester(participationRequest.getUser().getId())
            .build();
    }
}
