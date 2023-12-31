package com.example.RservationsService.controller;

import com.example.RservationsService.dto.AppointmentDto;
import com.example.RservationsService.dto.CategoryDto;
import com.example.RservationsService.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private AppointmentService appointmentService;

    public CategoryController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Get all clients")
    @GetMapping("/getCategory")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return new ResponseEntity<>(appointmentService.getCategory(), HttpStatus.OK);
    }
}
