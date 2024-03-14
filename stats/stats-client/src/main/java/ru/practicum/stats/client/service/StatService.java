package ru.practicum.stats.client.service;

import javax.servlet.http.HttpServletRequest;

public interface StatService {
    void addHit(final HttpServletRequest request);
}
