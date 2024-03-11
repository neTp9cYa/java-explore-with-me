package ru.practicum.ewm.service.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestDto;
import ru.practicum.ewm.service.mapper.api.ParticipationRequestMapper;
import ru.practicum.ewm.service.repository.ParticipationRequestRepository;
import ru.practicum.ewm.service.service.api.ParticipationRequestService;

@Service
@RequiredArgsConstructor
public class ParticipationRequestServiceImpl implements ParticipationRequestService {

    private final ParticipationRequestRepository participationRequestRepository;
    private final ParticipationRequestMapper participationRequestMapper;

    @Override
    public ParticipationRequestDto create(long userId, long eventId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ParticipationRequestDto cancel(long userId, long requestId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ParticipationRequestDto> getRequests(long userId) {
        throw new UnsupportedOperationException();
    }
}
