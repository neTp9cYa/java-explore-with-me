package ru.practicum.ewm.service.dto.event;

import ru.practicum.ewm.service.model.EventState;

public enum EventUpdateAdminStateAction {
    PUBLISH_EVENT,
    REJECT_EVENT;

    public EventState toEventState() {
        switch (this) {
            case PUBLISH_EVENT:
                return EventState.PUBLISHED;
            case REJECT_EVENT:
                return EventState.REJECTED;
            default:
                throw new IllegalArgumentException();
        }
    }
}
