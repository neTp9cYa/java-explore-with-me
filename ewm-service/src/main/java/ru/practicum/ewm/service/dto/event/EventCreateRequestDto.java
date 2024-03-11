package ru.practicum.ewm.service.dto.event;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class EventCreateRequestDto {
    private String annotation;
    private Long category;
    private String description;
    private LocalDateTime eventDate;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String title;
}
