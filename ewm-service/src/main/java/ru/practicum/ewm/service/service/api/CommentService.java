package ru.practicum.ewm.service.service.api;

import java.util.List;
import ru.practicum.ewm.service.dto.comment.CommentCreateRequestDto;
import ru.practicum.ewm.service.dto.comment.CommentFullDto;
import ru.practicum.ewm.service.dto.comment.CommentShortDto;
import ru.practicum.ewm.service.dto.comment.CommentUpdateAdminRequestDto;
import ru.practicum.ewm.service.dto.comment.CommentUpdateUserRequestDto;
import ru.practicum.ewm.service.service.request.GetCommentsAdminRequest;
import ru.practicum.ewm.service.service.request.GetCommentsPrivateRequest;
import ru.practicum.ewm.service.service.request.GetCommentsPublicRequest;

public interface CommentService {
    CommentFullDto createByUser(final long userId,
                                final long eventId,
                                final CommentCreateRequestDto commentCreateRequestDto);
    CommentFullDto updateByUser(final long userId,
                                final long eventId,
                                final CommentUpdateUserRequestDto commentUpdateUserRequestDto);

    CommentFullDto updateByAdmin(final long commentId,
                                 final CommentUpdateAdminRequestDto commentUpdateAdminRequestDto);

    CommentFullDto getOwnComment(final long userId,
                                 final long commentId);

    List<CommentFullDto> getOwnComments(final long userId,
                                        final GetCommentsPrivateRequest getCommentsPrivateRequest);

    List<CommentFullDto> getComments(final GetCommentsAdminRequest getCommentsAdminRequest);

    List<CommentShortDto> getComments(final GetCommentsPublicRequest getCommentsPublicRequest);
}
