package ru.practicum.ewm.service.service.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCompilationsRequest {
    private Boolean pinned;
    private long from;
    private int size;
}
