package ru.practicum.ewm.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestDto;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.mapper.api.ParticipationRequestMapper;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.model.ParticipationRequest;
import ru.practicum.ewm.service.model.ParticipationRequestStatus;
import ru.practicum.ewm.service.model.User;
import ru.practicum.ewm.service.repository.EventRepository;
import ru.practicum.ewm.service.repository.ParticipationRequestRepository;
import ru.practicum.ewm.service.repository.UserRepository;
import ru.practicum.ewm.service.service.api.ParticipationRequestService;

@Service
@RequiredArgsConstructor
public class ParticipationRequestServiceImpl implements ParticipationRequestService {

    private final ParticipationRequestRepository participationRequestRepository;
    private final ParticipationRequestMapper participationRequestMapper;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public ParticipationRequestDto create(final long userId, final long eventId) {
        final User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        final Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new NotFoundException(String.format("Event with id %d not found", eventId)));

        final ParticipationRequest creatingParticipationRequest = ParticipationRequest.builder()
            .user(user)
            .event(event)
            .status(ParticipationRequestStatus.PENDING)
            .createdOn(LocalDateTime.now())
            .build();

        final ParticipationRequest createdParticipationRequest = participationRequestRepository
            .save(creatingParticipationRequest);

        return participationRequestMapper.toParticipationRequestDto(createdParticipationRequest);
    }

    @Override
    @Transactional
    public ParticipationRequestDto cancel(final long userId, final long requestId) {
        final ParticipationRequest updatingParticipationRequest = participationRequestRepository
            .findByIdAndUser_Id(requestId, userId)
            .orElseThrow(() ->
                new NotFoundException(String.format("Participation request with id %d not found", requestId)));

        updatingParticipationRequest.setStatus(ParticipationRequestStatus.CANCELED);
        final ParticipationRequest updatedParticipationRequest = participationRequestRepository
            .save(updatingParticipationRequest);

        return participationRequestMapper.toParticipationRequestDto(updatedParticipationRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParticipationRequestDto> getRequests(final long userId) {
        final List<ParticipationRequest> participationRequests = participationRequestRepository
            .findAllByUser_Id(userId);
        return participationRequests.stream()
            .map(participationRequest -> participationRequestMapper.toParticipationRequestDto(participationRequest))
            .collect(Collectors.toList());
    }
}
