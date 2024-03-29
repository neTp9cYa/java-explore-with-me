package ru.practicum.ewm.service.controller.privateArea;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.participant.ParticipationRequestDto;
import ru.practicum.ewm.service.service.api.ParticipationRequestService;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
@Validated
public class ParticipantRequestPrivateController {

    private final ParticipationRequestService participationRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LogInputOutputAnnotaion
    public ParticipationRequestDto create(@PathVariable final long userId,
                                          @RequestParam final long eventId) {
        return participationRequestService.create(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    @LogInputOutputAnnotaion
    public ParticipationRequestDto cancel(@PathVariable final long userId,
                                          @PathVariable final long requestId) {
        return participationRequestService.cancel(userId, requestId);
    }


    @GetMapping
    @LogInputOutputAnnotaion
    public List<ParticipationRequestDto> getRequests(@PathVariable final long userId) {
        return participationRequestService.getRequests(userId);
    }
}
