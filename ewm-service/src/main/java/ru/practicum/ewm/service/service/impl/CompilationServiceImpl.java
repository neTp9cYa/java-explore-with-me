package ru.practicum.ewm.service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.service.dto.compilation.CompilationCreateRequestDto;
import ru.practicum.ewm.service.dto.compilation.CompilationDto;
import ru.practicum.ewm.service.dto.compilation.CompilationUpdateRequestDto;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.mapper.api.CompilationMapper;
import ru.practicum.ewm.service.model.Compilation;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.repository.CompilationRepository;
import ru.practicum.ewm.service.repository.specification.CompilationSpecification;
import ru.practicum.ewm.service.repository.specification.EventSpecification;
import ru.practicum.ewm.service.service.api.CompilationService;
import ru.practicum.ewm.service.service.request.GetCompilationsRequest;
import ru.practicum.utils.pagination.FlexPageRequest;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final CompilationMapper compilationMapper;

    @Override
    public CompilationDto create(final CompilationCreateRequestDto compilationDto) {
        final Compilation creatingCompilation = compilationMapper.toCompilation(compilationDto);
        final Compilation createdCompilation = compilationRepository.save(creatingCompilation);
        return compilationMapper.toCompilationDto(createdCompilation);
    }

    @Override
    public CompilationDto update(final long compilationId, final CompilationUpdateRequestDto compilationDto) {
        final Compilation updatingCompilation = compilationRepository.findById(compilationId)
            .orElseThrow(() ->
                new NotFoundException(String.format("Compilation with id %d not found", compilationId)));

        if (compilationDto.getEvents() != null) {
            //updatingCompilation.setEvents(compilationDto.getEvents());
        }

        if (compilationDto.getTitle() != null) {
            updatingCompilation.setTitle(compilationDto.getTitle());
        }

        if (compilationDto.getPinned() != null) {
            updatingCompilation.setPinned(compilationDto.getPinned());
        }

        final Compilation updatedCompilation = compilationRepository.save(updatingCompilation);

        return compilationMapper.toCompilationDto(updatedCompilation);
    }

    @Override
    public void delete(long compilationId) {
        final Compilation deletingCompilation = compilationRepository.findById(compilationId)
            .orElseThrow(() ->
                new NotFoundException(String.format("Compilation with id %d not found", compilationId)));

        compilationRepository.delete(deletingCompilation);
    }

    @Override
    public CompilationDto getCompilation(long compilationId) {
        final Compilation compilation = compilationRepository.findById(compilationId)
            .orElseThrow(() ->
                new NotFoundException(String.format("Compilation with id %d not found", compilationId)));

        return compilationMapper.toCompilationDto(compilation);
    }

    @Override
    public List<CompilationDto> getCompilations(GetCompilationsRequest getCompilationsRequest) {
        Specification<Compilation> specification = Specification
            .where(CompilationSpecification.pinned(getCompilationsRequest.getPinned()));

        final Pageable pageable = FlexPageRequest.of(
            getCompilationsRequest.getFrom(),
            getCompilationsRequest.getSize());

        final Page<Compilation> compilationPage = compilationRepository.findAll(specification, pageable);

        return compilationPage.stream()
            .map(event -> compilationMapper.toCompilationDto(event))
            .collect(Collectors.toList());
    }
}
