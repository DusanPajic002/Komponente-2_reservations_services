package com.example.RservationsService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EditHallDto {

    private Long managerID;
    private String oldHallName;
    private String hallName;
    private String description;
    private int numberOfTrainers;

    public EditHallDto(Long managerID, String oldHallName, String hallName, String description, int numberOfTrainers) {
        this.managerID = managerID;
        this.oldHallName = oldHallName;
        this.hallName = hallName;
        this.description = description;
        this.numberOfTrainers = numberOfTrainers;
    }
}
