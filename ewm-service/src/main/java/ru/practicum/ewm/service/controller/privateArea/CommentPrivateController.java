package ru.practicum.ewm.service.controller.privateArea;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.comment.CommentCreateRequestDto;
import ru.practicum.ewm.service.dto.comment.CommentFullDto;
import ru.practicum.ewm.service.dto.comment.CommentUpdateUserRequestDto;
import ru.practicum.ewm.service.model.CommentState;
import ru.practicum.ewm.service.service.api.CommentService;
import ru.practicum.ewm.service.service.request.GetCommentsPrivateRequest;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/users/{userId}/comments")
@RequiredArgsConstructor
@Validated
public class CommentPrivateController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LogInputOutputAnnotaion
    public CommentFullDto create(@PathVariable final long userId,
                                 @RequestBody @Valid final CommentCreateRequestDto commentCreateRequestDto) {
        return commentService.createByUser(userId, commentCreateRequestDto);
    }

    @PatchMapping("/{commentId}")
    @LogInputOutputAnnotaion
    public CommentFullDto update(@PathVariable final long userId,
                                 @RequestBody @Valid final CommentUpdateUserRequestDto commentUpdateUserRequestDto) {
        return commentService.updateByUser(userId, commentUpdateUserRequestDto);
    }

    @GetMapping("/{commentId}")
    @LogInputOutputAnnotaion
    public CommentFullDto getOwnComment(@PathVariable final long userId,
                                        @PathVariable final long commentId) {
        return commentService.getOwnComment(userId, commentId);
    }

    @GetMapping
    @LogInputOutputAnnotaion
    public List<CommentFullDto> getOwnComments(@PathVariable final long userId,
                                               @RequestParam(required = false) final List<Long> comments,
                                               @RequestParam(required = false) final List<Long> events,
                                               @RequestParam(required = false) final List<CommentState> states,
                                               @RequestParam(defaultValue = "0") @PositiveOrZero final long from,
                                               @RequestParam(defaultValue = "10") @Positive final int size) {
        final GetCommentsPrivateRequest getCommentsPrivateRequest = GetCommentsPrivateRequest.builder()
            .comments(comments)
            .events(events)
            .states(states)
            .from(from)
            .size(size)
            .build();

        return commentService.getOwnComments(userId, getCommentsPrivateRequest);
    }
}
