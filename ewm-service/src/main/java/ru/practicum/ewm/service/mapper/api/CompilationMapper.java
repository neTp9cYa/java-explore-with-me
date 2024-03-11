package ru.practicum.ewm.service.mapper.api;

import ru.practicum.ewm.service.dto.compilation.CompilationCreateRequestDto;
import ru.practicum.ewm.service.dto.compilation.CompilationDto;
import ru.practicum.ewm.service.model.Compilation;

public interface CompilationMapper {
    Compilation toCompilation(final CompilationCreateRequestDto compilationDto);
    CompilationDto toCompilationDto(final Compilation compilation);
}
