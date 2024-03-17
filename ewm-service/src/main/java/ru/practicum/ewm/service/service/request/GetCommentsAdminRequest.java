package ru.practicum.ewm.service.service.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.model.CommentState;

@Getter
@Builder
public class GetCommentsAdminRequest {
    private List<Long> comments;
    private List<Long> users;
    private List<Long> events;
    private List<CommentState> states;
    private long from;
    private int size;
}
