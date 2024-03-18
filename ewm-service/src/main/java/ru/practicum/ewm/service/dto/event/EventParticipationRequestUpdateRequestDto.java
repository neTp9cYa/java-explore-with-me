package ru.practicum.ewm.service.dto.event;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventParticipationRequestUpdateRequestDto {
    private List<Long> requestIds;
    private EventParticipationRequestStatus status;
}
