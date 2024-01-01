package com.example.RservationsService.mapper;

import com.example.RservationsService.domain.Appointment;
import com.example.RservationsService.domain.ClientAppointment;
import com.example.RservationsService.dto.ClientAppointmentDto;
import org.springframework.stereotype.Component;

@Component
public class ClientAppointmentMapper {

    public ClientAppointment clientAppointmentDtoToClientAppointment(Long clientId, Appointment appointment){
        return new ClientAppointment(clientId,appointment);
    }
}
