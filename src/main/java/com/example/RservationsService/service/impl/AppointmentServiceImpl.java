package com.example.RservationsService.service.impl;


import com.example.RservationsService.domain.Appointment;
import com.example.RservationsService.dto.AppointmentDto;
import com.example.RservationsService.dto.FilterDto;
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

    @Override
    public List<AppointmentDto> filterAppointments(FilterDto filterDto) {

        List<AppointmentDto>  filtered = appointmentRepository.findAll().stream().map(appointmentMapper::appointmentToAppointmentDto)
                .filter(appointmentDto -> appointmentDto.isAvailability() == true).collect(Collectors.toList());

        if(!filterDto.getCategory().equals("ignore"))
          filtered = filtered.stream().filter(appointmentDto -> appointmentDto.getTrainingCategory().equals(filterDto.getCategory())).collect(Collectors.toList());

        if(!filterDto.getDay().equals("ignore"))
          filtered = filtered.stream().filter(appointmentDto -> appointmentDto.getDay().equals(filterDto.getDay())).collect(Collectors.toList());

        if(!filterDto.getType().equals("ignore"))
          filtered = filtered.stream().filter(appointmentDto -> appointmentDto.getTrainingType().equals(filterDto.getType())).collect(Collectors.toList());

        return filtered;
    }

}

//        if(!filterDto.getCategory().equals("ignore")){
//            List<AppointmentDto>  f = appointmentRepository.findAll().stream().map(appointmentMapper::appointmentToAppointmentDto)
//                    .filter(appointmentDto -> appointmentDto.getTrainingCategory().equals(filterDto.getCategory()))
//                    .collect(Collectors.toList());
//        }
//        //filtriraj mi po danu osim ako je dan ponedeljak
//        if(!filterDto.getDay().equals("ignore")){
//            List<AppointmentDto>  f = appointmentRepository.findAll().stream().map(appointmentMapper::appointmentToAppointmentDto)
//                    .filter(appointmentDto -> appointmentDto.isAvailability() == true)
//                    .filter(appointmentDto -> appointmentDto.getDay().equals(filterDto.getDay()))
//                    .collect(Collectors.toList());
//        }