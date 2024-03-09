package ru.practicum.ewm.service.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompilationViewDto {
    private long id;
    private List<EventViewDto> events;
    private boolean pinned;
    private String title;
}
