package ru.practicum.ewm.service.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParticipationRequestCount {
    private long eventId;
    private long count;
}
