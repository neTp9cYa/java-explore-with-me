package ru.practicum.ewm.service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.service.dto.user.UserCreateRequestDto;
import ru.practicum.ewm.service.dto.user.UserFullDto;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.mapper.api.UserMapper;
import ru.practicum.ewm.service.model.User;
import ru.practicum.ewm.service.repository.UserRepository;
import ru.practicum.ewm.service.service.api.UserService;
import ru.practicum.ewm.service.service.request.GetUsersRequest;
import ru.practicum.utils.pagination.FlexPageRequest;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserFullDto create(final UserCreateRequestDto userDto) {
        final User creatingUser = userMapper.toUser(userDto);
        final User createdUser = userRepository.save(creatingUser);
        return userMapper.toUserFullDto(createdUser);
    }

    @Override
    public void delete(long userId) {
        final User deletingUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        userRepository.delete(deletingUser);
    }

    @Override
    public List<UserFullDto> getUsers(final GetUsersRequest getUsersRequest) {
        if (getUsersRequest.getIds() == null || getUsersRequest.getIds().isEmpty()) {
            final Pageable pageable = FlexPageRequest.of(getUsersRequest.getFrom(), getUsersRequest.getSize());
            final Page<User> usersPage = userRepository.findAll(pageable);
            return userMapper.toUserDtos(usersPage.toList());
        } else {
            final List<Long> userIds = getUsersRequest.getIds().stream()
                .skip(getUsersRequest.getFrom())
                .limit(getUsersRequest.getSize())
                .collect(Collectors.toList());
            final List<User> users = userRepository.findAllById(userIds);
            return userMapper.toUserDtos(users);
        }
    }
}
