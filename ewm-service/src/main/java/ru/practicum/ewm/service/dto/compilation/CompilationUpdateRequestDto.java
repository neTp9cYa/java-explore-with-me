package ru.practicum.ewm.service.dto.compilation;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CompilationUpdateRequestDto {
    private List<Long> events;
    private Boolean pinned;

    @Size(min = 1, max = 50)
    private String title;
}
