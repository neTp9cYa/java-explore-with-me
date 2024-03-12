package ru.practicum.ewm.service.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import ru.practicum.ewm.service.dto.Location.LocationCreateRequestDto;

@Getter
public class EventCreateRequestDto {
    @NotBlank
    @Range(min = 20, max = 2000)
    private String annotation;

    @NotNull
    @Positive
    private Long category;

    @NotBlank
    @Range(min = 20, max = 7000)
    private String description;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    @NotNull
    private LocationCreateRequestDto location;

    private boolean paid = false;
    private int participantLimit = 0;
    private boolean requestModeration = false;

    @NotBlank
    @Range(min = 3, max = 120)
    private String title;
}
