package ru.practicum.ewm.service.dto.event;

import java.util.List;
import lombok.Getter;

@Getter
public class EventParticipationRequestUpdateRequestDto {
    private List<Long> requestIds;
    private EventParticipationRequestStatus status;
}
