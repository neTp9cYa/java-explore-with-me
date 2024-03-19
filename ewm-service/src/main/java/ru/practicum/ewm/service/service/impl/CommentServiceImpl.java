package ru.practicum.ewm.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.service.dto.comment.CommentCreateRequestDto;
import ru.practicum.ewm.service.dto.comment.CommentFullDto;
import ru.practicum.ewm.service.dto.comment.CommentShortDto;
import ru.practicum.ewm.service.dto.comment.CommentUpdateAdminRequestDto;
import ru.practicum.ewm.service.dto.comment.CommentUpdateUserRequestDto;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.mapper.api.CommentMapper;
import ru.practicum.ewm.service.model.Comment;
import ru.practicum.ewm.service.model.CommentState;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.model.EventState;
import ru.practicum.ewm.service.model.User;
import ru.practicum.ewm.service.repository.CommentRepository;
import ru.practicum.ewm.service.repository.EventRepository;
import ru.practicum.ewm.service.repository.UserRepository;
import ru.practicum.ewm.service.repository.specification.CommentSpecification;
import ru.practicum.ewm.service.service.api.CommentService;
import ru.practicum.ewm.service.service.request.GetCommentsAdminRequest;
import ru.practicum.ewm.service.service.request.GetCommentsPrivateRequest;
import ru.practicum.ewm.service.service.request.GetCommentsPublicRequest;
import ru.practicum.utils.pagination.FlexPageRequest;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public CommentFullDto createByUser(final long userId,
                                       final long eventId,
                                       final CommentCreateRequestDto commentCreateRequestDto) {

        final User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        final Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new NotFoundException(String.format("Event with id %d not found", eventId)));

        if (event.getState() != EventState.PUBLISHED) {
            throw new IllegalArgumentException();
        }

        if (event.getUser().getId() == userId) {
            throw new IllegalStateException();
        }

        final Comment creatingComment = commentMapper.toComment(
            commentCreateRequestDto,
            user,
            event,
            CommentState.PENDING);

        final Comment createdComment = commentRepository.save(creatingComment);

        return commentMapper.toCommentFullDto(createdComment);
    }

    @Override
    public CommentFullDto updateByUser(final long userId,
                                       final long commentId,
                                       final CommentUpdateUserRequestDto commentDto) {

        final Comment updatingComment = commentRepository.findById(commentId)
            .orElseThrow(() -> new NotFoundException(String.format("Comment with id %d not found", commentId)));

        if (updatingComment.getUser().getId() != userId) {
            throw new NotFoundException(String.format("Comment with id %d not found", commentId));
        }

        if (updatingComment.getEvent().getState() != EventState.PUBLISHED) {
            throw new IllegalArgumentException();
        }

        if (updatingComment.getState() != CommentState.PENDING &&
            updatingComment.getState() != CommentState.CANCELED &&
            updatingComment.getState() != CommentState.REJECTED) {
            throw new IllegalStateException();
        }

        if (commentDto.getMessage() != null) {
            updatingComment.setMessage(commentDto.getMessage());
        }

        if (commentDto.getStateAction() != null) {
            switch (commentDto.getStateAction()) {
                case SEND_TO_REVIEW:
                    if (updatingComment.getState() != CommentState.CANCELED &&
                        updatingComment.getState() != CommentState.REJECTED) {
                        throw new IllegalStateException();
                    }
                    updatingComment.setState(CommentState.PENDING);
                    break;
                case CANCEL_REVIEW:
                    if (updatingComment.getState() != CommentState.PENDING) {
                        throw new IllegalStateException();
                    }
                    updatingComment.setState(CommentState.CANCELED);
                    break;
                default:
                    throw new IllegalStateException();
            }
        }

        final Comment updatedComment = commentRepository.save(updatingComment);

        return commentMapper.toCommentFullDto(updatedComment);
    }

    @Override
    public CommentFullDto updateByAdmin(final CommentUpdateAdminRequestDto commentDto) {


        final Comment updatingComment = commentRepository.findById(commentDto.getId())
            .orElseThrow(
                () -> new NotFoundException(String.format("Comment with id %d not found", commentDto.getId())));

        if (commentDto.getMessage() != null) {
            updatingComment.setMessage(commentDto.getMessage());
        }

        if (commentDto.getStateAction() != null) {
            switch (commentDto.getStateAction()) {
                case PUBLISH_COMMENT:
                    if (updatingComment.getState() != CommentState.PENDING) {
                        throw new IllegalStateException();
                    }
                    updatingComment.setState(CommentState.PUBLISHED);
                    updatingComment.setPublishedOn(LocalDateTime.now());
                    break;
                case REJECT_COMMENT:
                    if (updatingComment.getState() != CommentState.PENDING) {
                        throw new IllegalStateException();
                    }
                    updatingComment.setState(CommentState.REJECTED);
                    break;
                default:
                    throw new IllegalStateException();
            }
        }

        final Comment updatedComment = commentRepository.save(updatingComment);

        return commentMapper.toCommentFullDto(updatedComment);
    }

    @Override
    public CommentFullDto getOwnComment(final long userId,
                                        final long commentId) {

        final Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new NotFoundException(String.format("Comment with id %d not found", commentId)));

        if (comment.getUser().getId() != userId) {
            throw new NotFoundException(String.format("Comment with id %d not found", commentId));
        }

        return commentMapper.toCommentFullDto(comment);
    }

    @Override
    public List<CommentFullDto> getOwnComments(final long userId,
                                               final GetCommentsPrivateRequest getCommentsPrivateRequest) {

        final Specification<Comment> specification = Specification
            .where(CommentSpecification.comments(getCommentsPrivateRequest.getComments()))
            .and(CommentSpecification.user(userId))
            .and(CommentSpecification.events(getCommentsPrivateRequest.getEvents()))
            .and(CommentSpecification.states(getCommentsPrivateRequest.getStates()));

        final Pageable pageable = FlexPageRequest.of(
            getCommentsPrivateRequest.getFrom(),
            getCommentsPrivateRequest.getSize());

        final Page<Comment> commentPage = commentRepository.findAll(specification, pageable);

        return commentPage.stream()
            .map(comment -> commentMapper.toCommentFullDto(comment))
            .collect(Collectors.toList());
    }

    @Override
    public List<CommentFullDto> getComments(final GetCommentsAdminRequest getCommentsAdminRequest) {
        final Specification<Comment> specification = Specification
            .where(CommentSpecification.comments(getCommentsAdminRequest.getComments()))
            .and(CommentSpecification.users(getCommentsAdminRequest.getUsers()))
            .and(CommentSpecification.events(getCommentsAdminRequest.getEvents()))
            .and(CommentSpecification.states(getCommentsAdminRequest.getStates()));

        final Pageable pageable = FlexPageRequest.of(
            getCommentsAdminRequest.getFrom(),
            getCommentsAdminRequest.getSize());

        final Page<Comment> commentPage = commentRepository.findAll(specification, pageable);

        return commentPage.stream()
            .map(comment -> commentMapper.toCommentFullDto(comment))
            .collect(Collectors.toList());
    }

    @Override
    public List<CommentShortDto> getComments(final GetCommentsPublicRequest getCommentsPublicRequest) {

        final Specification<Comment> specification = Specification
            .where(CommentSpecification.eventStates(List.of(EventState.PUBLISHED)))
            .and(CommentSpecification.states(List.of(CommentState.PUBLISHED)))
            .and(CommentSpecification.events(getCommentsPublicRequest.getEvents()));

        final Pageable pageable = FlexPageRequest.of(
            getCommentsPublicRequest.getFrom(),
            getCommentsPublicRequest.getSize(),
            Sort.by("publishedOn"));

        final Page<Comment> commentPage = commentRepository.findAll(specification, pageable);

        return commentPage.stream()
            .map(comment -> commentMapper.toEventShortDto(comment))
            .collect(Collectors.toList());
    }
}
