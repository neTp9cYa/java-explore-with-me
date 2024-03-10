package ru.practicum.ewm.service.dto.event;

import java.time.LocalDateTime;
import ru.practicum.ewm.service.dto.location.LocationCreateDto;

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
