package ru.practicum.ewm.service.dto.Location;

import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LocationUpdateRequestDto {

    @PositiveOrZero
    private Float lat;

    @PositiveOrZero
    private Float lon;
}
