package ru.practicum.ewm.service.repository.specification;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import ru.practicum.ewm.service.model.Compilation;
import ru.practicum.ewm.service.model.Event;

public class CompilationSpecification {

    public static Specification<Compilation> pinned(final Boolean pinned) {
        if (pinned == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("pinned"), pinned);
    }
}
