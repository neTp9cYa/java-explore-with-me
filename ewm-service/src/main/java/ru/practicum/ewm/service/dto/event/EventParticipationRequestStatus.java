package ru.practicum.ewm.service.dto.event;

import ru.practicum.ewm.service.model.ParticipationRequestStatus;

public enum EventParticipationRequestStatus {
    CONFIRMED,
    REJECTED;

    public ParticipationRequestStatus toParticipationRequestStatus() {
        switch (this) {
            case CONFIRMED:
                return ParticipationRequestStatus.CONFIRMED;
            case REJECTED:
                return ParticipationRequestStatus.REJECTED;
            default:
                throw new IllegalArgumentException();
        }
    }
}
