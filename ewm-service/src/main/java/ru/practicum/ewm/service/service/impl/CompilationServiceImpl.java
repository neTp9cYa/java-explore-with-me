package ru.practicum.ewm.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.service.mapper.api.CompilationMapper;
import ru.practicum.ewm.service.repository.CompilationRepository;
import ru.practicum.ewm.service.service.api.CompilationService;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final CompilationMapper compilationMapper;
}
