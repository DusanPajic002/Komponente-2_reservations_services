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
    private String type;

    @OneToMany(mappedBy = "trainingCategory")
    private Set<Hall> halls;

    @OneToMany(mappedBy = "trainingCategory")
    private Set<Appointment> appointments = new HashSet<>();


    public TrainingCategory(String category, int price, String type) {
        this.category = category;
        this.price = price;
        this.type = type;

    }

    public TrainingCategory() {}



}
