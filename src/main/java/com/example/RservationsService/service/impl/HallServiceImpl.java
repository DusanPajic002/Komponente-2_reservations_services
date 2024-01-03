package com.example.RservationsService.service.impl;

import com.example.RservationsService.domain.Hall;
import com.example.RservationsService.dto.CategoryDto;
import com.example.RservationsService.dto.EditHallDto;
import com.example.RservationsService.dto.HallDto;
import com.example.RservationsService.listener.MessageHelper;
import com.example.RservationsService.mapper.AppointmentMapper;
import com.example.RservationsService.mapper.ClientAppointmentMapper;
import com.example.RservationsService.mapper.HallMapper;
import com.example.RservationsService.repository.AppointmentRepository;
import com.example.RservationsService.repository.ClientAppointmentRepository;
import com.example.RservationsService.repository.HallRepository;
import com.example.RservationsService.service.HallService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallServiceImpl implements HallService {
    private AppointmentRepository appointmentRepository;
    private MessageHelper messageHelper;
    private AppointmentMapper appointmentMapper;
    private ClientAppointmentMapper clientAppointmentMapper;
    private ClientAppointmentRepository clientAppointmentRepository;
    private HallRepository hallRepository;
    private HallMapper hallMapper;


    private RestTemplate clientServiceRestTemplate;

    public HallServiceImpl(AppointmentRepository appointmentRepository, MessageHelper messageHelper, AppointmentMapper appointmentMapper, ClientAppointmentMapper clientAppointmentMapper,
                           ClientAppointmentRepository clientAppointmentRepository, HallRepository hallRepository, HallMapper hallMapper,
                           RestTemplate clientServiceRestTemplate) {
        this.appointmentRepository = appointmentRepository;
        this.messageHelper = messageHelper;
        this.appointmentMapper = appointmentMapper;
        this.clientAppointmentMapper = clientAppointmentMapper;
        this.clientAppointmentRepository = clientAppointmentRepository;
        this.hallRepository = hallRepository;
        this.hallMapper = hallMapper;
        this.clientServiceRestTemplate = clientServiceRestTemplate;
    }

    @Override
    public List<HallDto> getHalls() {
        List<Hall> halls = hallRepository.findAll();
        return halls.stream().filter(hall -> hall.getManagerID() == 0).map(hallMapper::hallToHallDto).collect(Collectors.toList());
    }

    @Override
    public Integer setHallManager(String hallName, Long managerID) {
        System.out.println(hallName);
        System.out.println(managerID);
        Hall hall = hallRepository.findByName(hallName);
        System.out.println(hall);
        System.out.println(hall.getManagerID() + " pre |");
        if(hall != null){
            hall.setManagerID(managerID);
            System.out.println(managerID);
            System.out.println(hall.getManagerID());
            System.out.println(hall);
            hallRepository.save(hall);
            return 1;
        }
        return 0;
    }

    @Override
    public Integer editHall(EditHallDto editHallDto) {
        Hall hall = hallRepository.findByName(editHallDto.getOldHallName());
        if(hall != null){
            hall.setName(editHallDto.getHallName());
            hall.setDescription(editHallDto.getDescription());
            hall.setNumberOfTrainers(editHallDto.getNumberOfTrainers());
            clientServiceRestTemplate.exchange("/manager/updateHallName",
                    HttpMethod.PUT, new HttpEntity<>(new HallDto(editHallDto.getHallName(), editHallDto.getManagerID())), Integer.class);
            hallRepository.save(hall);
            return 1;
        }
        return 0;
    }
}
