package ru.practicum.ewm.service.dto.event;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.dto.user.UserShortDto;

@Getter
@Builder
public class EventShortDto {
    private long id;
    private boolean paid;
    private String title;
    private long views;
    private LocalDateTime eventDate;
    private long confirmedRequests;
    private String annotation;
    private CategoryDto category;
    private UserShortDto initiator;
}
