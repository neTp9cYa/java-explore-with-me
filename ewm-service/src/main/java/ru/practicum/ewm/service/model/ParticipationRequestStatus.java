package ru.practicum.ewm.service.model;

public enum ParticipationRequestStatus {
    PENDING,
    CANCELED,
    CONFIRMED,
    REJECTED;

    public static ParticipationRequestStatus from(final String statusName) {
        for (final ParticipationRequestStatus status : values()) {
            if (status.name().equalsIgnoreCase(statusName)) {
                return status;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown participation request status \"%s\"", statusName));
    }
}
