package com.example.RservationsService.controller;

import com.example.RservationsService.domain.Hall;
import com.example.RservationsService.dto.CategoryDto;
import com.example.RservationsService.dto.HallDto;
import com.example.RservationsService.service.AppointmentService;
import com.example.RservationsService.service.HallService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hall")
public class HallController {


    private AppointmentService appointmentService;
    private HallService hallService;

    public HallController(AppointmentService appointmentService, HallService hallService) {
        this.appointmentService = appointmentService;
        this.hallService = hallService;
    }

    @Operation(summary = "Get all halls")
    @GetMapping("/getHalls")
    public ResponseEntity<List<HallDto>> getCategories() {
        return new ResponseEntity<>(hallService.getHalls(), HttpStatus.OK);
    }

    @Operation(summary = "Set manager for hall")
    @PutMapping("/setHallManager")
    public ResponseEntity<Integer> setHallManager(@RequestBody HallDto hallDto) {
        return new ResponseEntity<>(hallService.setHallManager(hallDto.getName(), hallDto.getManagerID()), HttpStatus.OK);
    }

}
