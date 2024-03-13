package ru.practicum.ewm.service.dto.event;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestDto;

@Getter
@Builder
public class EventParticipationRequestUpdateResponseDto {
    private List<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
    private List<ParticipationRequestDto> rejectedRequests = new ArrayList<>();
}
