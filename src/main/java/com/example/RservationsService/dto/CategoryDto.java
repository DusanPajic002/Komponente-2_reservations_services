package com.example.RservationsService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String category; // powerlifting, pilates, kalistenika, joga, itd
    private int price;
    private String type;

    public CategoryDto(Long id, String category, int price, String type) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.type = type;
    }
}

