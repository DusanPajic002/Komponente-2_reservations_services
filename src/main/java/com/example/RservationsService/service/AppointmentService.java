package com.example.RservationsService.service;

import com.example.RservationsService.dto.AppointmentDto;
import com.example.RservationsService.dto.CategoryDto;
import com.example.RservationsService.dto.FilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> findAllAppointments();

    int updateTrainingCapacity(AppointmentDto appointmentID);
    List<AppointmentDto> filterAppointments (FilterDto filterDto);

    List<CategoryDto> getCategory();

}
