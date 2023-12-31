package com.example.RservationsService.controller;


import com.example.RservationsService.dto.AppointmentDto;
import com.example.RservationsService.dto.FilterDto;
import com.example.RservationsService.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentService  appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @Operation(summary = "Get all clients")
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        return new ResponseEntity<>(appointmentService.findAllAppointments(), HttpStatus.OK);
    }

    @Operation(summary = "Get all clients")
    @PostMapping("/filter")
    public ResponseEntity<List<AppointmentDto>> filterAppointments(@RequestBody FilterDto filterDto) {
        return new ResponseEntity<>(appointmentService.filterAppointments(filterDto), HttpStatus.OK);
    }

    @Operation(summary = "Update clinet trainings")
    @PutMapping("/updateClientTrainings")
    public ResponseEntity<Integer> updateClientTrainings(@RequestBody AppointmentDto appointmentDto) {
        return new ResponseEntity<>(appointmentService.updateTrainingCapacity(appointmentDto), HttpStatus.OK);
    }

}
