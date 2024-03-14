package ru.practicum.ewm.service.repository.specification;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import ru.practicum.ewm.service.model.Event;
import ru.practicum.ewm.service.model.EventState;

public class EventSpecification {

    public static Specification<Event> text(final String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }

        final String textLikeExpression = toLikeExpression(text);

        return (root, query, criteriaBuilder) ->
            criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("annotation")), textLikeExpression),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), textLikeExpression)
            );
    }

    public static Specification<Event> paid(final Boolean paid) {
        if (paid == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("paid"), paid);
    }

    public static Specification<Event> onlyAvailable(final Boolean onlyAvailable) {
        throw new UnsupportedOperationException();
    }

    public static Specification<Event> users(final List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            root.get("user").get("id").in(userIds);
    }

    public static Specification<Event> user(final Long userId) {
        if (userId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Event> states(final List<EventState> states) {
        if (states == null || states.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            root.get("state").in(states);
    }

    public static Specification<Event> categories(final List<Long> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            root.get("category").get("id").in(categoryIds);
    }

    public static Specification<Event> rangeStart(final LocalDateTime start) {
        if (start == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(root.get("eventDate"), start);
    }

    public static Specification<Event> rangeEnd(final LocalDateTime end) {
        if (end == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(root.get("eventDate"), end);
    }

    private static String toLikeExpression(final String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }

        return '%' + value.trim().toLowerCase() + "%";
    }
}
