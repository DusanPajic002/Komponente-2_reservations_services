package com.example.RservationsService.repository;

import com.example.RservationsService.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    //nadji mi sve appointmente koji imaju hall_id = hallId
    List<Appointment> findAllByHallId(Long hallId);

}
