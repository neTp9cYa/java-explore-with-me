package ru.practicum.ewm.service.dto.compilation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CompilationCreateRequestDto {
    private List<Long> events = new ArrayList<>();
    private boolean pinned = false;

    @NotBlank
    @Size(min = 1, max = 50)
    private String title;
}
