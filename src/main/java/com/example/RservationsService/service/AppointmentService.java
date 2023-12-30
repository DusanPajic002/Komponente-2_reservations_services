package com.example.RservationsService.service;

import com.example.RservationsService.dto.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentService {

    Page<AppointmentDto> findAllAppointments(Pageable pageable);
}
