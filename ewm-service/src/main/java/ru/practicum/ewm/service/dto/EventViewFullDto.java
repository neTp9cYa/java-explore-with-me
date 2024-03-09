package ru.practicum.ewm.service.dto;

import java.time.LocalDateTime;

public class EventViewFullDto {
    private long id;
    private boolean paid;
    private String title;
    private long views;
    private LocalDateTime eventDate;
    private long confirmedRequests;
    private String annotation;
    private CategoryViewDto category;
    private EvenViewInitiatorDto initiator;
    private LocalDateTime createdOn;
    private String description;
    private LocationViewDto location;
    private int participantLimit;
    private LocalDateTime publishedOn;
    private boolean requestModeration;
    private EventState state;
}
