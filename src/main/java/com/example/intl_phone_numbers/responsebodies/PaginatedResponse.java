package com.example.intl_phone_numbers.responsebodies;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginatedResponse<T> {

    private List<T> items;

    private long page;

    private long limit;

    private long offset;

    private long totalItems;
}
