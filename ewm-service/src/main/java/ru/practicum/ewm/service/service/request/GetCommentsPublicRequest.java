package ru.practicum.ewm.service.service.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.model.CommentState;

@Getter
@Builder
public class GetCommentsPublicRequest {
    private List<Long> events;
    private long from;
    private int size;
}
