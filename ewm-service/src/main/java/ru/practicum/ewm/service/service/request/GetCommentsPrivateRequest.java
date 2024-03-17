package ru.practicum.ewm.service.service.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.model.CommentState;
import ru.practicum.ewm.service.model.EventState;

@Getter
@Builder
public class GetCommentsPrivateRequest {
    private List<Long> comments;
    private List<Long> events;
    private List<CommentState> states;
    private long from;
    private int size;
}
