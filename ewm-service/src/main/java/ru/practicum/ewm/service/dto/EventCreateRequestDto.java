package ru.practicum.ewm.service.dto;

import java.time.LocalDateTime;

public class EventCreateRequestDto {
    private String annotation;
    private Long category;
    private String description;
    private LocalDateTime eventDate;
    private LocationCreateDto location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String title;
}
