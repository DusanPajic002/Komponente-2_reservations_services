package com.example.RservationsService.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentID;

    private String startTime;
    @Column(name = "day_of_week")
    private String day;
    private boolean availability; // slobodan/zauzet



    @ManyToOne
    @JoinColumn(name = "trainingCategory_id")
    private TrainingCategory trainingCategory;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;


    public Appointment(String startTime, String day, boolean availability, TrainingCategory trainingCategory, Hall hall) {
        this.startTime = startTime;
        this.day = day;
        this.availability = availability;
        this.trainingCategory = trainingCategory;
        this.hall = hall;
    }

    public Appointment() {
    }

}
