package com.example.RservationsService.service.impl;


import com.example.RservationsService.dto.AppointmentDto;
import com.example.RservationsService.mapper.AppointmentMapper;
import com.example.RservationsService.repository.AppointmentRepository;
import com.example.RservationsService.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private AppointmentMapper appointmentMapper;
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }
    @Override
    public Page<AppointmentDto> findAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable)
                .map(appointmentMapper::appointmentToAppointmentDto);
    }
}
