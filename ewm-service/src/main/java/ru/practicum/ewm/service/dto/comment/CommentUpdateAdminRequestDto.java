package ru.practicum.ewm.service.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import ru.practicum.ewm.service.model.CommentState;

@Getter
public class CommentUpdateAdminRequestDto {

    @Size(min = 20, max = 7000)
    private String message;

    private CommentUpdateAdminStateAction stateAction;
}
