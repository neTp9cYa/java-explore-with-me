package ru.practicum.ewm.service.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.practicum.ewm.service.dto.Location.LocationDto;
import ru.practicum.ewm.service.model.EventState;

@Getter
@Setter
@SuperBuilder
public class EventFullDto extends EventBaseDto {
    private String description;
    private int participantLimit;
    private boolean requestModeration;
    private EventState state;
    private LocationDto location;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
}
