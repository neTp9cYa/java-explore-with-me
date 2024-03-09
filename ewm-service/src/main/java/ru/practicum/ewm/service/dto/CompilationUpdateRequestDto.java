package ru.practicum.ewm.service.dto;

import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class CompilationUpdateRequestDto {
    private Optional<List<Long>> events;
    private boolean pinned;
    private String title;
}