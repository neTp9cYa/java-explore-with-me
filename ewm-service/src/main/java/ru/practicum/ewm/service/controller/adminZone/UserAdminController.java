package ru.practicum.ewm.service.controller.adminZone;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.UserCreateRequestDto;
import ru.practicum.ewm.service.dto.UserViewDto;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

    @PostMapping
    public UserViewDto create(@RequestBody final UserCreateRequestDto userCreateRequestDto) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable final long userId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public void search(@RequestParam final Optional<List<Long>> ids,
                       @RequestParam(defaultValue = "0") final long from,
                       @RequestParam(defaultValue = "10") final long size) {
        throw new UnsupportedOperationException();
    }
}
