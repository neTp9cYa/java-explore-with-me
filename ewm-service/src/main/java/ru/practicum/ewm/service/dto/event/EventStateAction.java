package ru.practicum.ewm.service.dto.event;

import ru.practicum.ewm.service.model.EventState;

public enum EventStateAction {
    SEND_TO_REVIEW,
    CANCEL_REVIEW;

    public EventState toEventState() {
        switch (this) {
            case SEND_TO_REVIEW:
                return EventState.PENDING;
            case CANCEL_REVIEW:
                return EventState.CANCELED;
            default:
                throw new IllegalArgumentException();
        }
    }
}
