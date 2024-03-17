package ru.practicum.ewm.service.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.service.dto.comment.CommentCreateRequestDto;
import ru.practicum.ewm.service.dto.comment.CommentFullDto;
import ru.practicum.ewm.service.dto.comment.CommentShortDto;
import ru.practicum.ewm.service.dto.comment.CommentUpdateAdminRequestDto;
import ru.practicum.ewm.service.dto.comment.CommentUpdateUserRequestDto;
import ru.practicum.ewm.service.service.api.CommentService;
import ru.practicum.ewm.service.service.request.GetCommentsAdminRequest;
import ru.practicum.ewm.service.service.request.GetCommentsPrivateRequest;
import ru.practicum.ewm.service.service.request.GetCommentsPublicRequest;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public CommentFullDto createByUser(long userId, long eventId,
                                       CommentCreateRequestDto commentCreateRequestDto) {
        return null;
    }

    @Override
    public CommentFullDto updateByUser(long userId, long eventId,
                                       CommentUpdateUserRequestDto commentUpdateUserRequestDto) {
        return null;
    }

    @Override
    public CommentFullDto updateByAdmin(long commentId,
                                        CommentUpdateAdminRequestDto commentUpdateAdminRequestDto) {
        return null;
    }

    @Override
    public CommentFullDto getOwnComment(long userId, long eventId, long commentId) {
        return null;
    }

    @Override
    public List<CommentFullDto> getOwnComments(long userId, GetCommentsPrivateRequest getCommentsPrivateRequest) {
        return new ArrayList<>();
    }

    @Override
    public List<CommentFullDto> getComments(GetCommentsAdminRequest getCommentsAdminRequest) {
        return null;
    }

    @Override
    public List<CommentShortDto> getComments(GetCommentsPublicRequest getCommentsPublicRequest) {
        return null;
    }
}
