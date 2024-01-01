package com.example.RservationsService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class ClientAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientID;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public ClientAppointment(Long clientID, Appointment appointment) {
        this.clientID = clientID;
        this.appointment = appointment;
    }

    public ClientAppointment() {

    }
}
