package ru.practicum.ewm.service.dto.Location;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class LocationCreateRequestDto {

    @NotNull
    @Positive
    private float lat;

    @NotNull
    @Positive
    private float lon;
}
