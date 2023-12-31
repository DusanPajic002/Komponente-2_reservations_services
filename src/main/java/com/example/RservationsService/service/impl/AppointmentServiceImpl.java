package com.example.RservationsService.service.impl;


import com.example.RservationsService.domain.Appointment;
import com.example.RservationsService.dto.AppointmentDto;
import com.example.RservationsService.mapper.AppointmentMapper;
import com.example.RservationsService.repository.AppointmentRepository;
import com.example.RservationsService.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private AppointmentMapper appointmentMapper;
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }
    @Override
    public List<AppointmentDto> findAllAppointments() {
        return appointmentRepository.findAll().stream().map(appointmentMapper::appointmentToAppointmentDto)
                .filter(appointmentDto -> appointmentDto.isAvailability() == true)
                .collect(Collectors.toList());
    }
    @Override
    public int updateTrainingCapacity(AppointmentDto appointmentID) {
        Appointment appointment = appointmentRepository.findById(appointmentID.getId()).orElse(null);
        int price = appointment.getTrainingCategory().getPrice();
        appointment.increaseCapacity();
        if(appointment.getCapacity() == 0)
            appointment.setAvailability(false);
        System.out.println(appointment.getCapacity());
        appointmentRepository.save(appointment);
        return price;
    }
}
