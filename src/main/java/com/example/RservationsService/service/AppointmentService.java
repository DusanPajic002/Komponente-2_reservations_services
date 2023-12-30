package com.example.RservationsService.service;

import com.example.RservationsService.dto.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> findAllAppointments();
}
