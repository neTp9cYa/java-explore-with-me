package ru.practicum.ewm.service.service.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.model.EventState;

@Getter
@Builder
public class GetEventsAdminRequest {
    private List<Long> users;
    private List<EventState> states;
    private List<Long> categories;
    private LocalDateTime rangeStart;
    private LocalDateTime rangeEnd;
    private long from;
    private int size;
}
