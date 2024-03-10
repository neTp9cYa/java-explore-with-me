package ru.practicum.ewm.service.service.api;

import java.util.List;
import ru.practicum.ewm.service.dto.user.UserCreateRequestDto;
import ru.practicum.ewm.service.dto.user.UserDto;
import ru.practicum.ewm.service.service.request.GetUsersRequest;

public interface UserService {
    UserDto create(final UserCreateRequestDto userDto);
    void delete(final long userId);
    List<UserDto> getUsers(final GetUsersRequest getUsersRequest);
}
