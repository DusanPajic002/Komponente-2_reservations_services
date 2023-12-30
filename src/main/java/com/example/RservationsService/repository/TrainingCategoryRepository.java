package com.example.RservationsService.repository;

import com.example.RservationsService.domain.TrainingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingCategoryRepository extends JpaRepository<TrainingCategory, Long> {
}
