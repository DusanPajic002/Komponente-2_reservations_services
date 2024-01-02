package com.example.RservationsService.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientAppointmentDto {

    private Long clientId;

    private Long appointmentId;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    public ClientAppointmentDto(Long clientId, Long appointmentId, String firstName, String lastName, String email, String username) {
        this.clientId = clientId;
        this.appointmentId = appointmentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }

    @Override
    public String toString() {
        return "ClientAppointmentDto{" +
                "clientId=" + clientId +
                ", appointmentId=" + appointmentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
