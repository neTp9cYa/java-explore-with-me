package ru.practicum.ewm.service.controller.publicArea;

import java.util.List;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.compilation.CompilationViewDto;
import ru.practicum.ewm.service.service.api.CompilationService;

@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class CompilationPublicController {

    private final CompilationService compilationService;

    @GetMapping("/{compilationId}")
    public CompilationViewDto get(@PathVariable final long compilationId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<CompilationViewDto> search(@RequestParam final Boolean pinned,
                                           @RequestParam(defaultValue = "0") @PositiveOrZero final long from,
                                           @RequestParam(defaultValue = "10") @Positive final long size) {
        throw new UnsupportedOperationException();
    }
}
