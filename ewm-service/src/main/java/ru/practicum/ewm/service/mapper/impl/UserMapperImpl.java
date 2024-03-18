package ru.practicum.ewm.service.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.service.dto.user.UserCreateRequestDto;
import ru.practicum.ewm.service.dto.user.UserFullDto;
import ru.practicum.ewm.service.dto.user.UserShortDto;
import ru.practicum.ewm.service.mapper.api.UserMapper;
import ru.practicum.ewm.service.model.User;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toUser(final UserCreateRequestDto userCreateRequestDto) {
        return User.builder()
            .name(userCreateRequestDto.getName())
            .email(userCreateRequestDto.getEmail())
            .build();
    }

    @Override
    public UserFullDto toUserFullDto(final User user) {
        return UserFullDto.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .build();
    }

    @Override
    public UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
            .id(user.getId())
            .name(user.getName())
            .build();
    }

    @Override
    public List<UserFullDto> toUserDtos(final List<User> users) {
        return users.stream().map(this::toUserFullDto).collect(Collectors.toList());
    }
}
