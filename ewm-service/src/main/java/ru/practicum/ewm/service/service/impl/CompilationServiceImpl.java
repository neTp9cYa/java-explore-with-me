package ru.practicum.ewm.service.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.dto.compilation.CompilationCreateRequestDto;
import ru.practicum.ewm.service.dto.compilation.CompilationDto;
import ru.practicum.ewm.service.dto.compilation.CompilationUpdateRequestDto;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.mapper.api.CompilationMapper;
import ru.practicum.ewm.service.model.Compilation;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.repository.CompilationRepository;
import ru.practicum.ewm.service.repository.EventRepository;
import ru.practicum.ewm.service.repository.specification.CompilationSpecification;
import ru.practicum.ewm.service.service.api.CompilationService;
import ru.practicum.ewm.service.service.request.GetCompilationsRequest;
import ru.practicum.utils.pagination.FlexPageRequest;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final CompilationMapper compilationMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public CompilationDto create(final CompilationCreateRequestDto compilationDto) {
        final Compilation creatingCompilation = compilationMapper.toCompilation(compilationDto);

        if (compilationDto.getEvents().size() > 0) {
            final List<Event> events = eventRepository.findAllById(compilationDto.getEvents());
            if (compilationDto.getEvents().size() != events.size()) {
                throw new IllegalArgumentException();
            }
            creatingCompilation.setEvents(new HashSet<>(events));
        }

        final Compilation createdCompilation = compilationRepository.save(creatingCompilation);
        return compilationMapper.toCompilationDto(createdCompilation);
    }

    @Override
    @Transactional
    public CompilationDto update(final long compilationId, final CompilationUpdateRequestDto compilationDto) {
        final Compilation updatingCompilation = compilationRepository.findById(compilationId)
            .orElseThrow(() ->
                new NotFoundException(String.format("Compilation with id %d not found", compilationId)));

        if (compilationDto.getEvents() != null) {
            if (compilationDto.getEvents().size() == 0) {
                updatingCompilation.setEvents(new HashSet<>());
            } else {
                final List<Event> events = eventRepository.findAllById(compilationDto.getEvents());
                if (compilationDto.getEvents().size() != events.size()) {
                    throw new IllegalArgumentException();
                }
                updatingCompilation.setEvents(new HashSet<>(events));
            }
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
    @Transactional
    public void delete(long compilationId) {
        final Compilation deletingCompilation = compilationRepository.findById(compilationId)
            .orElseThrow(() ->
                new NotFoundException(String.format("Compilation with id %d not found", compilationId)));

        compilationRepository.delete(deletingCompilation);
    }

    @Override
    @Transactional(readOnly = true)
    public CompilationDto getCompilation(long compilationId) {
        final Compilation compilation = compilationRepository.findById(compilationId)
            .orElseThrow(() ->
                new NotFoundException(String.format("Compilation with id %d not found", compilationId)));

        return compilationMapper.toCompilationDto(compilation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompilationDto> getCompilations(GetCompilationsRequest getCompilationsRequest) {
        final Specification<Compilation> specification = Specification
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
