package com.example.RservationsService.repository;

import com.example.RservationsService.domain.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {

    Hall findByName(String name);
}
