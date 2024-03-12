package ru.practicum.ewm.service.dto.Location;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationDto {
    private float lat;
    private float lon;
}
