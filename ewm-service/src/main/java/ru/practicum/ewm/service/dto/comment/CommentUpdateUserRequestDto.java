package ru.practicum.ewm.service.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentUpdateUserRequestDto {

    @NotBlank
    @Size(min = 20, max = 7000)
    private String message;

    private CommentUpdateUserStateAction stateAction;
}
