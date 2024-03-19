package ru.practicum.ewm.service.controller.publicArea;

import java.util.List;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.service.dto.comment.CommentShortDto;
import ru.practicum.ewm.service.service.api.CommentService;
import ru.practicum.ewm.service.service.request.GetCommentsPublicRequest;
import ru.practicum.utils.log.LogInputOutputAnnotaion;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Validated
public class CommentPublicController {

    private final CommentService commentService;

    @GetMapping
    @LogInputOutputAnnotaion
    public List<CommentShortDto> getAll(
        @RequestParam(required = false) final List<Long> events,
        @RequestParam(defaultValue = "0") @PositiveOrZero final long from,
        @RequestParam(defaultValue = "10") @Positive final int size) {

        final GetCommentsPublicRequest getCommentsPublicRequest = GetCommentsPublicRequest.builder()
            .events(events)
            .from(from)
            .size(size)
            .build();

        return commentService.getComments(getCommentsPublicRequest);
    }
}
