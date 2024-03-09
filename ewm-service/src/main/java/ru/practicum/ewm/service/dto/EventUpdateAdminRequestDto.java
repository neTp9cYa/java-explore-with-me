package ru.practicum.ewm.service.dto;

import java.time.LocalDateTime;

public class EventUpdateAdminRequestDto {
    private String annotation;
    private Long category;
    private String description;
    private LocalDateTime eventDate;
    private LocationUpdateDto location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private EventStateAdminAction stateAction;
    private String title;
}
