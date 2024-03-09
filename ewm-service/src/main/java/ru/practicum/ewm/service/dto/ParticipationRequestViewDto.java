package ru.practicum.ewm.service.dto;

import java.time.LocalDateTime;

public class ParticipationRequestViewDto {
    private LocalDateTime created;
    private long event;
    private long id;
    private long requester;
    private ParticipationRequestStatus status;
}
