package com.example.RservationsService.controller;


import com.example.RservationsService.dto.*;
import com.example.RservationsService.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentService  appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @Operation(summary = "Get all clients")
    @PostMapping("/all")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments(@RequestBody Long id) {
        return new ResponseEntity<>(appointmentService.findAllAppointments(id), HttpStatus.OK);
    }

    @Operation(summary = "Filter appointments")
    @PostMapping("/filter")
    public ResponseEntity<List<AppointmentDto>> filterAppointments(@RequestBody FilterDto filterDto) {
        return new ResponseEntity<>(appointmentService.filterAppointments(filterDto), HttpStatus.OK);
    }
    @Operation(summary = "Get client appointments")
    @PostMapping("/getCleintAppointment")
    public ResponseEntity<List<AppointmentDto>> getCleintAppointment(@RequestBody String clientId) {
        return new ResponseEntity<>(appointmentService.getCleintAppointment(clientId), HttpStatus.OK);
    }

    @Operation(summary = "Update clinet trainings")
    @PutMapping("/updateClientTrainings")
    public ResponseEntity<Integer> updateClientTrainings(@RequestBody ClientAppointmentDto clientAppointmentDto) {
        return new ResponseEntity<>(appointmentService.updateTrainingCapacity(clientAppointmentDto), HttpStatus.OK);
    }
    @Operation(summary = "Cancel training")
    @PostMapping("/cancelAppointment")
    public ResponseEntity<Integer> cancelAppointment(@RequestBody ClientAppointmentDto clientAppointmentDto) {
        System.out.println(clientAppointmentDto);
        return new ResponseEntity<>(appointmentService.cancelAppointment(clientAppointmentDto), HttpStatus.OK);
    }

    @Operation(summary = "Cancel training")
    @PostMapping("/managerCancelAppointment")
    public ResponseEntity<Integer> managerCancelAppointment(@RequestBody ClientAppointmentDto clientAppointmentDto) {
        System.out.println(clientAppointmentDto);
        return new ResponseEntity<>(appointmentService.managerCancelAppointment(clientAppointmentDto), HttpStatus.OK);
    }

    @Operation(summary = "Get notifications by username")
    @GetMapping("/{hallName}")
    public ResponseEntity<Set<AppointmentDto>> showNotifications(@PathVariable String hallName) {
        Set<AppointmentDto> notificationDtos = appointmentService.listAppointments(hallName);
        return  new ResponseEntity<>(notificationDtos, HttpStatus.OK);
    }



//    @Operation(summary = "Add appointment")
//    @PostMapping("/addAppointment")
//    public ResponseEntity<AppointmentDto> updateClientTrainings(@RequestBody AppointmentCreateDto appointmentCreateDto) {
//        return new ResponseEntity<>(appointmentService.addAppointment(appointmentCreateDto), HttpStatus.OK);
//    }

}
