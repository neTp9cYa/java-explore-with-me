package ru.practicum.ewm.service.service.api;

import java.util.List;
import ru.practicum.ewm.service.dto.user.UserCreateRequestDto;
import ru.practicum.ewm.service.dto.user.UserFullDto;
import ru.practicum.ewm.service.service.request.GetUsersRequest;

public interface UserService {
    UserFullDto create(final UserCreateRequestDto userDto);

    void delete(final long userId);

    List<UserFullDto> getUsers(final GetUsersRequest getUsersRequest);
}
