package ru.practicum.ewm.service.dto.participant;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public class ParticipationRequestDto {
    private LocalDateTime created;
    private long event;
    private long id;
    private long requester;
    private ParticipationRequestStatus status;
}
