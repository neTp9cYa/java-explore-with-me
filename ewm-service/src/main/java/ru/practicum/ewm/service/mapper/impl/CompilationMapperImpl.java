package ru.practicum.ewm.service.mapper.impl;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.service.dto.compilation.CompilationCreateRequestDto;
import ru.practicum.ewm.service.dto.compilation.CompilationDto;
import ru.practicum.ewm.service.mapper.api.CompilationMapper;
import ru.practicum.ewm.service.mapper.api.EventMapper;
import ru.practicum.ewm.service.model.Compilation;

@Component
@RequiredArgsConstructor
public class CompilationMapperImpl implements CompilationMapper {

    private final EventMapper eventMapper;

    @Override
    public Compilation toCompilation(final CompilationCreateRequestDto compilationDto) {
        return Compilation.builder()
            .pinned(compilationDto.isPinned())
            .title(compilationDto.getTitle())
            //.events(compilationDto.getEvents())
            .build();
    }

    @Override
    public CompilationDto toCompilationDto(Compilation compilation) {
        return CompilationDto.builder()
            .id(compilation.getId())
            .pinned(compilation.getPinned())
            .title(compilation.getTitle())
            .events(compilation.getEvents().stream()
                .map(event -> eventMapper.toEventShortDto(event))
                .collect(Collectors.toList()))
            .build();
    }
}
