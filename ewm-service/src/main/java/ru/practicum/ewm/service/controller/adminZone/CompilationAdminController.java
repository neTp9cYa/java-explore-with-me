package ru.practicum.ewm.service.controller.adminZone;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.CompilationCreateRequestDto;
import ru.practicum.ewm.service.dto.CompilationUpdateRequestDto;
import ru.practicum.ewm.service.dto.CompilationViewDto;

@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
public class CompilationAdminController {

    @PostMapping
    public CompilationViewDto create(@RequestBody final CompilationCreateRequestDto compilationCreateRequestDto) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/compilationId")
    public CompilationViewDto update(@RequestBody final CompilationUpdateRequestDto compilationUpdateRequestDto) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{compilationId}")
    public void delete(@PathVariable final long compilationId) {
        throw new UnsupportedOperationException();
    }
}
