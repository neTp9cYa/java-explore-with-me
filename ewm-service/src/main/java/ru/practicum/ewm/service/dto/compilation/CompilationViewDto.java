package ru.practicum.ewm.service.dto.compilation;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.dto.event.EventShortDto;

@Getter
@Builder
public class CompilationViewDto {
    private long id;
    private List<EventShortDto> events;
    private boolean pinned;
    private String title;
}
