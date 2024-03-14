package ru.practicum.ewm.service.service.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCategoriesRequest {
    private long from;
    private int size;
}
