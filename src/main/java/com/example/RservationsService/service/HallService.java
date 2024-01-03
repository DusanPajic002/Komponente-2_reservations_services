package com.example.RservationsService.service;

import com.example.RservationsService.dto.CategoryDto;
import com.example.RservationsService.dto.EditHallDto;
import com.example.RservationsService.dto.HallDto;

import java.util.List;

public interface HallService {
    List<HallDto> getHalls();
    Integer setHallManager(String hallName,Long managerID);

    Integer editHall(EditHallDto editHallDto);

}
