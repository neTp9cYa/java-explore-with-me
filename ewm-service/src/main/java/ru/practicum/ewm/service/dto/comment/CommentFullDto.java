package ru.practicum.ewm.service.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import ru.practicum.ewm.service.dto.user.UserFullDto;
import ru.practicum.ewm.service.model.CommentState;

@Builder
@Getter
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
