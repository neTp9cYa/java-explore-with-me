package ru.practicum.ewm.service.dto.event;

public enum EventSort {
    EVENT_DATE,
    VIEWS;

    public String toSortProperty() {
        switch (this) {
            case EVENT_DATE:
                return "eventDate";
            case VIEWS:
                return "id";
            default:
                throw new IllegalArgumentException();
        }
    }
}
