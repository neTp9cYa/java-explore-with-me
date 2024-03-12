package ru.practicum.utils.exception;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiErrorDto {
    @Singular
    private List<String> errors;
    private String message;
    private String reason;
    private HttpStatus status;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
