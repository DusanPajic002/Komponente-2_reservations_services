package com.example.RservationsService.service;

import com.example.RservationsService.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> findAllAppointments(Long clientId);
    int updateTrainingCapacity(ClientAppointmentDto clientAppointmentDto);
    List<AppointmentDto> filterAppointments (FilterDto filterDto);
    List<CategoryDto> getCategory();
    AppointmentDto addAppointment(AppointmentCreateDto appointmentCreateDto);
    List<AppointmentDto> getCleintAppointment (String cleintId);
    int cancelAppointment(ClientAppointmentDto clientAppointmentDto);
    List<AppointmentDto> listAppointments(String username);

}
