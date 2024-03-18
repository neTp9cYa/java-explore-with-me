package ru.practicum.ewm.service.dto.comment;

import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentUpdateAdminRequestDto {

    @Size(min = 20, max = 7000)
    private String message;

    private CommentUpdateAdminStateAction stateAction;
}
