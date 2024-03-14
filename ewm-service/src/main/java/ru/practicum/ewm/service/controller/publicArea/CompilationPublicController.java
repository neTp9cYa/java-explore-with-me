package ru.practicum.ewm.service.controller.publicArea;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.compilation.CompilationDto;
import ru.practicum.ewm.service.service.api.CompilationService;
import ru.practicum.stats.client.aspect.CollectRequestStatisticAnnotaion;
import ru.practicum.stats.client.service.StatService;
import ru.practicum.ewm.service.service.request.GetCompilationsRequest;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Validated
public class CompilationPublicController {

    private final CompilationService compilationService;
    private final StatService statService;

    @GetMapping("/{compilationId}")
    @LogInputOutputAnnotaion
    @CollectRequestStatisticAnnotaion
    public CompilationDto get(@PathVariable(name = "compId") final long compilationId) {
        return compilationService.getCompilation(compilationId);
    }

    @GetMapping
    @LogInputOutputAnnotaion
    @CollectRequestStatisticAnnotaion
    public List<CompilationDto> search(@RequestParam(required = false) final Boolean pinned,
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
