package ru.practicum.ewm.service.dto;

import java.util.List;

public class EventParticipationRequestStatusUpdateDto {
    private List<Long> requestIds;
    private ParticipationRequestUpdateStatus status;
}
