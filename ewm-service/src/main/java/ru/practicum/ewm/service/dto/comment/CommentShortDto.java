package ru.practicum.ewm.service.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import ru.practicum.ewm.service.dto.user.UserFullDto;
import ru.practicum.ewm.service.dto.user.UserShortDto;
import ru.practicum.ewm.service.model.CommentState;

public class CommentShortDto {
    private Long id;
    private UserShortDto user;
    private Long eventId;
    private String message;
    private CommentState state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
}
