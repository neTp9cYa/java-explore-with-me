package ru.practicum.ewm.service.mapper.impl;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.service.dto.comment.CommentCreateRequestDto;
import ru.practicum.ewm.service.dto.comment.CommentFullDto;
import ru.practicum.ewm.service.dto.comment.CommentShortDto;
import ru.practicum.ewm.service.mapper.api.CommentMapper;
import ru.practicum.ewm.service.mapper.api.UserMapper;
import ru.practicum.ewm.service.model.Comment;
import ru.practicum.ewm.service.model.CommentState;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.model.User;

@Component
@RequiredArgsConstructor
public class CommentMapperImpl implements CommentMapper {

    private final UserMapper userMapper;

    @Override
    public CommentFullDto toCommentFullDto(final Comment comment) {
        return CommentFullDto.builder()
            .id(comment.getId())
            .user(userMapper.toUserFullDto(comment.getUser()))
            .eventId(comment.getEvent().getId())
            .message(comment.getMessage())
            .state(comment.getState())
            .createdOn(comment.getCreatedOn())
            .publishedOn(comment.getPublishedOn())
            .build();
    }

    @Override
    public CommentShortDto toEventShortDto(final Comment comment) {
        return CommentShortDto.builder()
            .id(comment.getId())
            .user(userMapper.toUserShortDto(comment.getUser()))
            .eventId(comment.getEvent().getId())
            .message(comment.getMessage())
            .publishedOn(comment.getPublishedOn())
            .build();
    }

    @Override
    public Comment toComment(final CommentCreateRequestDto commentDto,
                             final User user,
                             final Event event,
                             final CommentState state) {
        return Comment.builder()
            .user(user)
            .event(event)
            .message(commentDto.getMessage())
            .state(state)
            .createdOn(LocalDateTime.now())
            .build();
    }
}