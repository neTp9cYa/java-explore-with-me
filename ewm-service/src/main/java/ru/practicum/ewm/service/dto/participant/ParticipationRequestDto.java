package ru.practicum.ewm.service.dto.participant;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.model.ParticipationRequestStatus;

@Builder
@Getter
public class ParticipationRequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private long event;
    private long id;
    private long requester;
    private ParticipationRequestStatus status;
}
