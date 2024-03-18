package ru.practicum.ewm.service.dto.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserShortDto {
    private long id;
    private String name;
}
