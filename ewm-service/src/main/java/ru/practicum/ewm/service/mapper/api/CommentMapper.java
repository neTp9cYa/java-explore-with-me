package ru.practicum.ewm.service.mapper.api;

import ru.practicum.ewm.service.dto.comment.CommentCreateRequestDto;
import ru.practicum.ewm.service.dto.comment.CommentFullDto;
import ru.practicum.ewm.service.dto.comment.CommentShortDto;
import ru.practicum.ewm.service.model.Comment;
import ru.practicum.ewm.service.model.CommentState;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.model.User;

public interface CommentMapper {
    CommentFullDto toCommentFullDto(final Comment comment);

    CommentShortDto toEventShortDto(final Comment comment);

    Comment toComment(final CommentCreateRequestDto commentDto,
                      final User user,
                      final Event event,
                      final CommentState state);
}
