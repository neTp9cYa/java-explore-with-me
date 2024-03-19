package ru.practicum.ewm.service.controller.adminArea;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.comment.CommentFullDto;
import ru.practicum.ewm.service.dto.comment.CommentUpdateAdminRequestDto;
import ru.practicum.ewm.service.model.CommentState;
import ru.practicum.ewm.service.service.api.CommentService;
import ru.practicum.ewm.service.service.request.GetCommentsAdminRequest;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
@Validated
public class CommentAdminController {

    private final CommentService commentService;

    @PatchMapping
    @LogInputOutputAnnotaion
    public CommentFullDto update(@RequestBody @Valid final CommentUpdateAdminRequestDto commentDto) {
        return commentService.update(commentDto);
    }

    @GetMapping
    @LogInputOutputAnnotaion
    public List<CommentFullDto> getComments(
        @RequestParam(required = false) final List<Long> comments,
        @RequestParam(required = false) final List<Long> users,
        @RequestParam(required = false) final List<Long> events,
        @RequestParam(required = false) final List<CommentState> states,
        @RequestParam(defaultValue = "0") @PositiveOrZero final long from,
        @RequestParam(defaultValue = "10") @Positive final int size) {

        final GetCommentsAdminRequest getCommentsAdminRequest = GetCommentsAdminRequest.builder()
            .comments(comments)
            .users(users)
            .events(events)
            .states(states)
            .from(from)
            .size(size)
            .build();

        return commentService.getComments(getCommentsAdminRequest);
    }

}
