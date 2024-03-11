package ru.practicum.ewm.service.service.api;

import java.util.List;
import ru.practicum.ewm.service.dto.compilation.CompilationCreateRequestDto;
import ru.practicum.ewm.service.dto.compilation.CompilationDto;
import ru.practicum.ewm.service.dto.compilation.CompilationUpdateRequestDto;
import ru.practicum.ewm.service.service.request.GetCompilationsRequest;

public interface CompilationService {
    CompilationDto create(final CompilationCreateRequestDto compilationDto);
    CompilationDto update(final long compilationId, final CompilationUpdateRequestDto compilationDto);
    void delete(final long compilationId);
    CompilationDto getCompilation(final long compilationId);
    List<CompilationDto> getCompilations(final GetCompilationsRequest getCompilationsRequest);
}
