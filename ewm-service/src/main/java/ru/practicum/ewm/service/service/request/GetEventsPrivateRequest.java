package ru.practicum.ewm.service.service.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetEventsPrivateRequest {
    private long from;
    private int size;
}
