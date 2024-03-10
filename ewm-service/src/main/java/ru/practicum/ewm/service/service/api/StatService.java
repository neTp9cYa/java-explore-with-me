package ru.practicum.ewm.service.service.api;

import javax.servlet.http.HttpServletRequest;

public interface StatService {
    void addHit(final HttpServletRequest request);
}
