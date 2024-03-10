package ru.practicum.ewm.service.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class UserCreateRequestDto {

    @NotBlank
    @Range(min = 2, max = 250)
    private String name;

    @NotBlank
    @Range(min = 6, max = 254)
    @Email
    private String email;
}
