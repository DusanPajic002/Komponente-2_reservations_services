package com.example.RservationsService.mapper;

import com.example.RservationsService.domain.Hall;
import com.example.RservationsService.dto.HallDto;
import org.springframework.stereotype.Component;

@Component
public class HallMapper {

    public HallDto hallToHallDto(Hall hall){
        return new HallDto(hall.getName(), hall.getManagerID());
    }

}
