package ru.practicum.ewm.service.dto.event;

import java.time.LocalDateTime;
import ru.practicum.ewm.service.dto.location.LocationUpdateDto;

public class EventUpdateRequestDto {
    private String annotation;
    private Long category;
    private String description;
    private LocalDateTime eventDate;
    private LocationUpdateDto location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private EventStateAction stateAction;
    private String title;
}
