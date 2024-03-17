package ru.practicum.ewm.service.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import ru.practicum.ewm.service.dto.Location.LocationDto;
import ru.practicum.ewm.service.dto.user.UserFullDto;
import ru.practicum.ewm.service.dto.user.UserShortDto;
import ru.practicum.ewm.service.model.CommentState;
import ru.practicum.ewm.service.model.EventState;

public class CommentFullDto {
    private Long id;
    private UserFullDto user;
    private Long eventId;
    private String message;
    private CommentState state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
}
