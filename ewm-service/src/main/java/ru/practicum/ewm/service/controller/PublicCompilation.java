package ru.practicum.ewm.service.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.service.dto.CompilationViewDto;

@RequestMapping("/compilations")
public class PublicCompilation {

    @GetMapping("/{compilationId}")
    public CompilationViewDto get(@PathVariable final long compilationId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<CompilationViewDto> search(@RequestParam final Boolean pinned,
                                           @RequestParam(defaultValue = "0") final long from,
                                           @RequestParam(defaultValue = "10") final long size) {
        throw new UnsupportedOperationException();
    }
}
