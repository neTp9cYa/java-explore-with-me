package ru.practicum.ewm.service.dto.event;

import java.time.LocalDateTime;
import lombok.Builder;
import ru.practicum.ewm.service.dto.category.CategoryDto;

@Builder
public class EventFullDto {
    private long id;
    private boolean paid;
    private String title;
    private long views;
    private LocalDateTime eventDate;
    private long confirmedRequests;
    private String annotation;
    private CategoryDto category;
    private EventViewInitiatorDto initiator;
    private LocalDateTime createdOn;
    private String description;
    private int participantLimit;
    private LocalDateTime publishedOn;
    private boolean requestModeration;
    private EventState state;
}
