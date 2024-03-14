package ru.practicum.ewm.service.dto.Location;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class LocationCreateRequestDto {

    @NotNull
    @PositiveOrZero
    private Float lat;

    @NotNull
    @PositiveOrZero
    private Float lon;
}
