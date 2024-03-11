package ru.practicum.ewm.service.dto.event;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class EventUpdateRequestDto {
    private String annotation;
    private Long category;
    private String description;
    private LocalDateTime eventDate;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private EventStateAction stateAction;
    private String title;
}
