package ru.practicum.ewm.service.dto.comment;

import ru.practicum.ewm.service.model.CommentState;

public enum CommentUpdateUserStateAction {
    PUBLISH_COMMENT,
    CANCEL_COMMENT;

    public CommentState toEventState() {
        switch (this) {
            case PUBLISH_COMMENT:
                return CommentState.PENDING;
            case CANCEL_COMMENT:
                return CommentState.CANCELED;
            default:
                throw new IllegalArgumentException();
        }
    }
}
