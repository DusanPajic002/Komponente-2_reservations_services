package com.example.RservationsService.service;

import com.example.RservationsService.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface AppointmentService {

    List<AppointmentDto> findAllAppointments(Long clientId);
    int updateTrainingCapacity(ClientAppointmentDto clientAppointmentDto);
    List<AppointmentDto> filterAppointments (FilterDto filterDto);
    List<CategoryDto> getCategory();
    AppointmentDto addAppointment(AppointmentCreateDto appointmentCreateDto);
    List<AppointmentDto> getCleintAppointment (String cleintId);
    int cancelAppointment(ClientAppointmentDto clientAppointmentDto);
    Set<AppointmentDto> listAppointments(String username);
    int managerAllowAppointment(ClientAppointmentDto clientAppointmentDto);
    int managerCancelAppointment(ClientAppointmentDto clientAppointmentDto);


}
