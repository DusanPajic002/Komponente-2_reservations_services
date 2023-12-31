package com.example.RservationsService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDto {
    private String category;
    private String day;
    private String type;

    public FilterDto() {
    }
}
