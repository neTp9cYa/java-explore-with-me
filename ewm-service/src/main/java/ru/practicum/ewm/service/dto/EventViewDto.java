package ru.practicum.ewm.service.dto;

import java.time.LocalDateTime;

public class EventViewDto {
    private long id;
    private boolean paid;
    private String title;
    private long views;
    private LocalDateTime eventDate;
    private long confirmedRequests;
    private String annotation;
    private CategoryViewDto category;
    private EvenViewInitiatorDto initiator;
}
