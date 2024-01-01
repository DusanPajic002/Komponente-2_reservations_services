package com.example.RservationsService.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentCreateDto {
    private String category;
    private String time;
    private String dayOfWeek;


    public AppointmentCreateDto(String category, String time, String dayOfWeek) {
        this.category = category;
        this.time = time;
        this.dayOfWeek = dayOfWeek;
    }
}
