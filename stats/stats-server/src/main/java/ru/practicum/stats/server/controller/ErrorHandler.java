package ru.practicum.stats.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.utils.exception.ApiErrorDto;
import ru.practicum.utils.exception.ExceptionHelper;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ErrorHandler {

    private final ExceptionHelper exceptionHelper;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorDto handleThrowable(final Throwable e) {
        log.error("Unexpected error occured", e);
        return ApiErrorDto.builder()
            .reason("Unexpected error occured")
            .message(e.getLocalizedMessage())
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .error(exceptionHelper.getStackTrace(e))
            .build();
    }
}
