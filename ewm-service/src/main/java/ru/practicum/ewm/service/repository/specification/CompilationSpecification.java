package ru.practicum.ewm.service.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.practicum.ewm.service.model.Compilation;

public class CompilationSpecification {

    public static Specification<Compilation> pinned(final Boolean pinned) {
        if (pinned == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("pinned"), pinned);
    }
}
