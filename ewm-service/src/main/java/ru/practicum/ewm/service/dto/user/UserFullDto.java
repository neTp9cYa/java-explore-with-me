package ru.practicum.ewm.service.dto.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserFullDto {
    private Long id;
    private String email;
    private String name;
}
