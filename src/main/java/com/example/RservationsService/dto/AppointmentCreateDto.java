package com.example.RservationsService.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentCreateDto {

    private String category;
    private String dayOfWeek;
    private String time;
    private Long managerId;

    public AppointmentCreateDto(String category, String dayOfWeek, String time, Long managerId) {
        this.category = category;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.managerId = managerId;
    }

    public AppointmentCreateDto() {
    }

    @Override
    public String toString() {
        return "AppointmentCreateDto{" +
                "category='" + category + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", time='" + time + '\'' +
                ", managerId=" + managerId +
                '}';
    }
}
