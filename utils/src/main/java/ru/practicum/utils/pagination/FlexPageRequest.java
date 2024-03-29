package ru.practicum.utils.pagination;

import org.springframework.data.domain.Sort;

public class FlexPageRequest extends FlexPageable {

    protected FlexPageRequest(final long offset, final int limit, final Sort sort) {
        super(offset, limit, sort);
    }

    public static FlexPageRequest of(final long offset, final int limit) {

        return of(offset, limit, Sort.unsorted());
    }

    public static FlexPageRequest of(final long offset, final int limit, final Sort sort) {
        return new FlexPageRequest(offset, limit, sort);
    }
}
