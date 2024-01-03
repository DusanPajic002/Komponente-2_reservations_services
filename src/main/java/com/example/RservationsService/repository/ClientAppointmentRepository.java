package com.example.RservationsService.repository;

import com.example.RservationsService.domain.ClientAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientAppointmentRepository extends JpaRepository<ClientAppointment, Long>{
    List<ClientAppointment> findByClientID(Long clientId);
    ClientAppointment findByAppointmentIDAndClientID(Long appointmentId, Long clientId);

    List<ClientAppointment> findByAppointmentID(Long appointmentId);
}
