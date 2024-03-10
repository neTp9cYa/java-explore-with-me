package ru.practicum.ewm.service.service.request;

import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUsersRequest {
    private List<Long> ids;
    private long from;
    private int size;
}
