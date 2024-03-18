package ru.practicum.ewm.service.dto.Location;

import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationUpdateRequestDto {

    @PositiveOrZero
    private Float lat;

    @PositiveOrZero
    private Float lon;
}
