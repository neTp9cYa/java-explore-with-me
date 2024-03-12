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
import ru.practicum.ewm.service.dto.compilation.CompilationDto;
import ru.practicum.ewm.service.service.api.CompilationService;
import ru.practicum.ewm.service.service.request.GetCategoriesRequest;
import ru.practicum.ewm.service.service.request.GetCompilationsRequest;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class CompilationPublicController {

    private final CompilationService compilationService;

    @GetMapping("/{compilationId}")
    @LogInputOutputAnnotaion
    public CompilationDto get(@PathVariable(name = "compId") final long compilationId) {
        return compilationService.getCompilation(compilationId);
    }

    @GetMapping
    @LogInputOutputAnnotaion
    public List<CompilationDto> search(@RequestParam final Boolean pinned,
                                       @RequestParam(defaultValue = "0") @PositiveOrZero final long from,
                                       @RequestParam(defaultValue = "10") @Positive final int size) {
        final GetCompilationsRequest getCompilationsRequest = GetCompilationsRequest.builder()
            .pinned(pinned)
            .from(from)
            .size(size)
            .build();

        return compilationService.getCompilations(getCompilationsRequest);
    }
}
