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
    @OneToMany(mappedBy = "hall")
    private Set<HallXTrainingCategory> hallTrainingCategories = new HashSet<>();


    public Hall( String name, int managerID, String description, int numberOfTrainers) {
        this.name = name;
        this.managerID = managerID;
        this.description = description;
        this.numberOfTrainers = numberOfTrainers;
    }


}
