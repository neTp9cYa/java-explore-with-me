package ru.practicum.ewm.service.service.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.dto.event.EventSort;

@Getter
@Builder
public class GetEventsPrivateRequest {
    private long from;
    private int size;
}
