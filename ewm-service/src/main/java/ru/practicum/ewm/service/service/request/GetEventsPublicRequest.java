package ru.practicum.ewm.service.service.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.dto.event.EventSort;
import ru.practicum.ewm.service.dto.event.EventState;

@Getter
@Builder
public class GetEventsPublicRequest {
    private EventState state;
    private String text;
    private List<Long> categories;
    private Boolean paid;
    private LocalDateTime rangeStart;
    private LocalDateTime rangeEnd;
    private boolean onlyAvailable;
    private EventSort sort;
    private long from;
    private long size;
}
