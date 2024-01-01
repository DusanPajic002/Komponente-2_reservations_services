package com.example.RservationsService.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String startTime;
    @Column(name = "day_of_week")
    private String day;
    private boolean availability; // slobodan/zauzet
    private int capacity;



    @ManyToOne
    @JoinColumn(name = "trainingCategory_id")
    private TrainingCategory trainingCategory;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToMany(mappedBy = "appointment")
    private Set<ClientAppointment> clientAppointments = new HashSet<>();


    public Appointment(String startTime, String day, boolean availability, TrainingCategory trainingCategory, Hall hall) {
        this.startTime = startTime;
        this.day = day;
        this.availability = availability;
        this.trainingCategory = trainingCategory;
        this.hall = hall;
        if(trainingCategory.getType() == "group")
            this.capacity = 12;
        else if(trainingCategory.getType() == "individual")
            this.capacity = 1;
    }

    public Appointment() {
    }

    public void increaseCapacity() {
        this.capacity--;
    }
}
