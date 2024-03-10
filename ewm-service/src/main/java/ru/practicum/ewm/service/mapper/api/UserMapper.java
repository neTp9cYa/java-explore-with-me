package ru.practicum.ewm.service.mapper.api;

import java.util.List;
import ru.practicum.ewm.service.dto.user.UserCreateRequestDto;
import ru.practicum.ewm.service.dto.user.UserDto;
import ru.practicum.ewm.service.model.User;

public interface UserMapper {
    User toUser(final UserCreateRequestDto userCreateRequestDto);
    UserDto toUserDto(final User user);
    List<UserDto> toUserDtos(final List<User> users);
}
