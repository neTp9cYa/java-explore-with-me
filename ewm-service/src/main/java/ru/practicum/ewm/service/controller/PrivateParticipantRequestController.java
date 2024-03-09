package ru.practicum.ewm.service.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.service.dto.ParticipationRequestViewDto;

@RequestMapping("/users/{userId}/requests")
public class PrivateParticipantRequestController {

    @PostMapping
    public ParticipationRequestViewDto create(@PathVariable final long userId,
                                              @RequestParam final long eventId) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestViewDto cancel(@PathVariable final long userId,
                                              @PathVariable final long requestId) {
        throw new UnsupportedOperationException();
    }


    @PatchMapping("/{requestId}/cancel")
    public List<ParticipationRequestViewDto> getRequests(@PathVariable final long userId,
                                                         @PathVariable final long requestId) {
        throw new UnsupportedOperationException();
    }
}
