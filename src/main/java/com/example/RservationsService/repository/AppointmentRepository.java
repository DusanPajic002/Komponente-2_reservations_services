package com.example.RservationsService.repository;

import com.example.RservationsService.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


}
