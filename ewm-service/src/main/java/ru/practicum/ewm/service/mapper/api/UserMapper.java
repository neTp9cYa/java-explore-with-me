package ru.practicum.ewm.service.mapper.api;

import java.util.List;
import ru.practicum.ewm.service.dto.user.UserCreateRequestDto;
import ru.practicum.ewm.service.dto.user.UserFullDto;
import ru.practicum.ewm.service.dto.user.UserShortDto;
import ru.practicum.ewm.service.model.User;

public interface UserMapper {
    User toUser(final UserCreateRequestDto userCreateRequestDto);

    UserFullDto toUserFullDto(final User user);

    UserShortDto toUserShortDto(final User user);

    List<UserFullDto> toUserDtos(final List<User> users);
}
