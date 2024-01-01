package com.example.RservationsService.repository;

import com.example.RservationsService.domain.ClientAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientAppointmentRepository extends JpaRepository<ClientAppointment, Long>{
    List<ClientAppointment> findByClientID(Long clientId);
}
