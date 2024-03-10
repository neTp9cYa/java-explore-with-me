package ru.practicum.ewm.service.dto.event;

import java.time.LocalDateTime;
import ru.practicum.ewm.service.dto.category.CategoryDto;

public class EventShortDto {
    private long id;
    private boolean paid;
    private String title;
    private long views;
    private LocalDateTime eventDate;
    private long confirmedRequests;
    private String annotation;
    private CategoryDto category;
    private EventViewInitiatorDto initiator;
}
