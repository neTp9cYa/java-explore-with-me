package ru.practicum.ewm.service.dto.Location;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class LocationUpdateRequestDto {

    @PositiveOrZero
    private Float lat;

    @PositiveOrZero
    private Float lon;
}
