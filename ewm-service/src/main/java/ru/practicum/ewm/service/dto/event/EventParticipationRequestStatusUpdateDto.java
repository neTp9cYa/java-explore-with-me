package ru.practicum.ewm.service.dto.event;

import java.util.List;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestUpdateStatus;

public class EventParticipationRequestStatusUpdateDto {
    private List<Long> requestIds;
    private ParticipationRequestUpdateStatus status;
}
