package com.example.RservationsService.dto;

import com.example.RservationsService.domain.Hall;
import com.example.RservationsService.domain.TrainingCategory;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDto {

    private Long id;
    private String startTime;

    private String day;
    private boolean availability; // slobodan/zauzet
    private String trainingCategory;
    private String trainingType;
    private String hall;
    private String hallDescription;


    public AppointmentDto(Long id, String startTime, String day, boolean availability, String trainingCategory, String trainingType, String hall, String hallDescription) {
        this.id = id;
        this.startTime = startTime;
        this.day = day;
        this.availability = availability;
        this.trainingCategory = trainingCategory;
        this.trainingType = trainingType;
        this.hall = hall;
        this.hallDescription = hallDescription;
    }
}
