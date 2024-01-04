package com.example.RservationsService.mapper;

import com.example.RservationsService.domain.Appointment;
import com.example.RservationsService.domain.Hall;
import com.example.RservationsService.domain.TrainingCategory;
import com.example.RservationsService.dto.AppointmentCreateDto;
import com.example.RservationsService.dto.AppointmentDto;
import com.example.RservationsService.repository.TrainingCategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentDto appointmentToAppointmentDto(Appointment appointment){
        return new AppointmentDto(appointment.getID(),appointment.getStartTime(),appointment.getDay(),appointment.isAvailability(),
                appointment.getTrainingCategory().getCategory(),appointment.getTrainingCategory().getType(),appointment.getHall().getName(),
                appointment.getHall().getDescription());
    }

    public Appointment createAppointment(AppointmentCreateDto appointment, TrainingCategory trainingCategory, Hall hall){
        return new Appointment(appointment.getTime(),appointment.getDayOfWeek(),true,trainingCategory, hall);
    }


}
