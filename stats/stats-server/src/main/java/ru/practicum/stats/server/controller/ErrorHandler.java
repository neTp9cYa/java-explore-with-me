package ru.practicum.stats.server.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.practicum.utils.exception.ApiErrorDto;
import ru.practicum.utils.exception.ExceptionHelper;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private final ExceptionHelper exceptionHelper;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorDto handleIllegalArgumentException(final IllegalArgumentException e) {
        log.error("Illegal arguments", e);
        return ApiErrorDto.builder()
            .reason("The passed data are not correct")
            .message(e.getLocalizedMessage())
            .status(HttpStatus.BAD_REQUEST)
            .error(exceptionHelper.getStackTrace(e))
            .build();
    }

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

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException e,
                                                               final HttpHeaders headers,
                                                               final HttpStatus status,
                                                               final WebRequest request) {
        log.error("Method arguments are not valid", e);
        final List<String> errors = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError -> String.format("Field: %s. Error: %s. Value: %s",
                fieldError.getField(),
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue()))
            .collect(Collectors.toList());

        final ApiErrorDto apiErrorDto = ApiErrorDto.builder()
            .reason("The passed data are not valid")
            .message(e.getLocalizedMessage())
            .status(HttpStatus.BAD_REQUEST)
            .errors(errors)
            .build();

        return new ResponseEntity<>(apiErrorDto, new HttpHeaders(), apiErrorDto.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        final MissingServletRequestParameterException e,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
        final ApiErrorDto apiErrorDto = ApiErrorDto.builder()
            .reason("The required query parameter is absent")
            .message(e.getLocalizedMessage())
            .status(HttpStatus.BAD_REQUEST)
            .build();

        return new ResponseEntity<>(apiErrorDto, new HttpHeaders(), apiErrorDto.getStatus());
    }
}
