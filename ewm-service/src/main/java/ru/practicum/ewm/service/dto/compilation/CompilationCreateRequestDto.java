package ru.practicum.ewm.service.dto.compilation;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CompilationCreateRequestDto {
    private Optional<List<Long>> events;
    private boolean pinned;

    @NotBlank
    @Max(50)
    private String title;
}
