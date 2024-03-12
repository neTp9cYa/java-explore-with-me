package ru.practicum.ewm.service.controller.adminArea;

import java.util.List;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.user.UserCreateRequestDto;
import ru.practicum.ewm.service.dto.user.UserFullDto;
import ru.practicum.ewm.service.service.api.UserService;
import ru.practicum.ewm.service.service.request.GetUsersRequest;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserService userService;

    @PostMapping
    @LogInputOutputAnnotaion
    public UserFullDto create(@RequestBody final UserCreateRequestDto userDto) {
        return userService.create(userDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @LogInputOutputAnnotaion
    public void delete(@PathVariable final long userId) {
        userService.delete(userId);
    }

    @GetMapping
    @LogInputOutputAnnotaion
    public List<UserFullDto> getUsers(@RequestParam(required = false) final List<Long> ids,
                                      @RequestParam(defaultValue = "0") @PositiveOrZero final long from,
                                      @RequestParam(defaultValue = "10") @Positive final int size) {
        final GetUsersRequest getUsersRequest = GetUsersRequest.builder()
            .ids(ids)
            .from(from)
            .size(size)
            .build();
        return userService.getUsers(getUsersRequest);
    }
}
