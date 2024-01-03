package com.example.RservationsService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationFromManagerDto {
    //appointment.getHall().getName(), appointment.getDay(), appointment.getStartTime()

    private Long clientId;
    private String managerFirstName;
    private String managerLastName;
    private String hallName;
    private String day;
    private String startTime;

    public NotificationFromManagerDto(Long clientId, String managerFirstName, String managerLastName, String hallName, String day, String startTime) {
        this.clientId = clientId;
        this.managerFirstName = managerFirstName;
        this.managerLastName = managerLastName;
        this.hallName = hallName;
        this.day = day;
        this.startTime = startTime;
    }

    public NotificationFromManagerDto() {
    }
}
