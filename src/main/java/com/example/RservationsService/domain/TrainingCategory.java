package com.example.RservationsService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(columnList = "category", unique = true),
})
public class TrainingCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category; // powerlifting, pilates, kalistenika, joga, itd
    private int price;

    @ManyToOne
    @JoinColumn(name = "trainingType_id")
    private TrainingType trainingType;

    @OneToMany(mappedBy = "trainingCategory")
    private Set<HallXTrainingCategory> hallXTrainingCategorySet = new HashSet<>();


    public TrainingCategory() {}

}
