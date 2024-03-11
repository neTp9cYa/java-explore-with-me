package ru.practicum.ewm.service.dto.event;

import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
public class EventUpdateAdminRequestDto {

    @Range(min = 20, max = 2000)
    private String annotation;

    private Long category;

    @Range(min = 20, max = 7000)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private Boolean paid;

    private Integer participantLimit;

    private Boolean requestModeration;

    private EventUpdateAdminStateAction stateAction;

    @Range(min = 3, max = 120)
    private String title;
}
