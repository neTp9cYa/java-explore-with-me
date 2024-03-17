package ru.practicum.ewm.service.repository.specification;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import ru.practicum.ewm.service.model.Comment;
import ru.practicum.ewm.service.model.CommentState;

public class CommentSpecification {

    public static Specification<Comment> comments(final List<Long> commentIds) {
        if (commentIds == null || commentIds.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            root.get("id").in(commentIds);
    }

    public static Specification<Comment> users(final List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            root.get("user").get("id").in(userIds);
    }

    public static Specification<Comment> events(final List<Long> eventIds) {
        if (eventIds == null || eventIds.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            root.get("event").get("id").in(eventIds);
    }

    public static Specification<Comment> user(final Long userId) {
        if (userId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Comment> states(final List<CommentState> states) {
        if (states == null || states.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            root.get("state").in(states);
    }
}
