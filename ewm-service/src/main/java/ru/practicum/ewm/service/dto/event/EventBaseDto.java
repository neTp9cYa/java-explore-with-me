package ru.practicum.ewm.service.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.practicum.ewm.service.dto.category.CategoryDto;
import ru.practicum.ewm.service.dto.user.UserShortDto;

@Getter
@Setter
@SuperBuilder
public abstract class EventBaseDto {
    private long id;
    private boolean paid;
    private String title;
    private long views;
    private long confirmedRequests;
    private String annotation;
    private CategoryDto category;
    private UserShortDto initiator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
}
