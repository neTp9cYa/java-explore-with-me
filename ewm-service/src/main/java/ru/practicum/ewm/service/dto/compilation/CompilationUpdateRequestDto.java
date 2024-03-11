package ru.practicum.ewm.service.dto.compilation;

import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class CompilationUpdateRequestDto {
    private Optional<List<Long>> events;
    private Boolean pinned;
    private String title;
}
