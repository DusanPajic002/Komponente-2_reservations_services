package com.example.RservationsService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int managerID;
    private String description;
    private int numberOfTrainers;

    @ManyToOne
    @JoinColumn(name = "trainingCategory_id")
    private TrainingCategory trainingCategory;

    @OneToMany(mappedBy = "hall")
    private Set<Appointment> appointments = new HashSet<>();


    public Hall(String name, String description, int numberOfTrainers, TrainingCategory trainingCategory) {
        this.name = name;
        this.description = description;
        this.numberOfTrainers = numberOfTrainers;
        this.trainingCategory = trainingCategory;
    }

    public Hall() {
    }


}
