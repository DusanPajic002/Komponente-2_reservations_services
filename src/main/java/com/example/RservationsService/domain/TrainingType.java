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
        @Index(columnList = "type", unique = true),
})
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @OneToMany(mappedBy = "type")
    private Set<TrainingCategory> trainingCategory = new HashSet<>();

    public TrainingType() {}


}
