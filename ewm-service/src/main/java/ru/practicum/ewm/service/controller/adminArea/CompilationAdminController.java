package ru.practicum.ewm.service.controller.adminArea;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.compilation.CompilationCreateRequestDto;
import ru.practicum.ewm.service.dto.compilation.CompilationDto;
import ru.practicum.ewm.service.dto.compilation.CompilationUpdateRequestDto;
import ru.practicum.ewm.service.service.api.CompilationService;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Validated
public class CompilationAdminController {

    private final CompilationService compilationService;

    @PostMapping
    @LogInputOutputAnnotaion
    public CompilationDto create(@RequestBody @Valid final CompilationCreateRequestDto compilationDto) {
        return compilationService.create(compilationDto);
    }

    @PatchMapping("/{compilationId}")
    @LogInputOutputAnnotaion
    public CompilationDto update(@PathVariable final long compilationId,
                                 @RequestBody @Valid final CompilationUpdateRequestDto compilationDto) {
        return compilationService.update(compilationId, compilationDto);
    }

    @DeleteMapping("/{compilationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @LogInputOutputAnnotaion
    public void delete(@PathVariable final long compilationId) {
        compilationService.delete(compilationId);;
    }
}
