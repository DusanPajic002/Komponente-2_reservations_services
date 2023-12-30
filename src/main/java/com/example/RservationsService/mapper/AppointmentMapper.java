package com.example.RservationsService.mapper;

import com.example.RservationsService.domain.Appointment;
import com.example.RservationsService.dto.AppointmentDto;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public AppointmentDto appointmentToAppointmentDto(Appointment appointment){
        return new AppointmentDto(appointment.getAppointmentID(),appointment.getStartTime(),appointment.getDay(),appointment.isAvailability(),
                appointment.getTrainingCategory().getCategory(),appointment.getTrainingCategory().getType(),appointment.getHall().getName(),
                appointment.getHall().getDescription());
    }

}
