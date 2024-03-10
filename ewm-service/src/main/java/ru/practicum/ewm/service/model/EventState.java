package ru.practicum.ewm.service.model;

public enum EventState {
    PENDING,
    PUBLISHED,
    REJECTED,
    CANCELED;

    public static EventState from(final String stateName) {
        for (final EventState state : values()) {
            if (state.name().equalsIgnoreCase(stateName)) {
                return state;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown event state \"%s\"", stateName));
    }
}
