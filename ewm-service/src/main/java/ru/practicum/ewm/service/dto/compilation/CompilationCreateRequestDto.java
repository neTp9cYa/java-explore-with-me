package ru.practicum.ewm.service.dto.compilation;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CompilationCreateRequestDto {
    private Optional<List<Long>> events;
    private boolean pinned;

    @NotBlank
    @Size(min = 1, max = 50)
    private String title;
}
