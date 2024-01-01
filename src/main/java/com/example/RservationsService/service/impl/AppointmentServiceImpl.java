package com.example.RservationsService.service.impl;


import com.example.RservationsService.domain.Appointment;
import com.example.RservationsService.domain.ClientAppointment;
import com.example.RservationsService.domain.TrainingCategory;
import com.example.RservationsService.dto.*;
import com.example.RservationsService.listener.MessageHelper;
import com.example.RservationsService.mapper.AppointmentMapper;
import com.example.RservationsService.mapper.CategoryMapper;
import com.example.RservationsService.mapper.ClientAppointmentMapper;
import com.example.RservationsService.repository.AppointmentRepository;
import com.example.RservationsService.repository.ClientAppointmentRepository;
import com.example.RservationsService.repository.TrainingCategoryRepository;
import com.example.RservationsService.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private TrainingCategoryRepository trainingCategoryRepository;
    private AppointmentMapper appointmentMapper;
    private CategoryMapper categoryMapper;

    private RestTemplate clientServiceRestTemplate;

    private ClientAppointmentMapper clientAppointmentMapper;

    private ClientAppointmentRepository clientAppointmentRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    private MessageHelper messageHelper;

    private String schedulingMessage;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, TrainingCategoryRepository trainingCategoryRepository, AppointmentMapper appointmentMapper,
                                  CategoryMapper categoryMapper, RestTemplate clientServiceRestTemplate, ClientAppointmentMapper clientAppointmentMapper,
                                  ClientAppointmentRepository clientAppointmentRepository, MessageHelper messageHelper, @Value("${destination.schedulingMessage}") String schedulingMessage) {
        this.appointmentRepository = appointmentRepository;
        this.trainingCategoryRepository = trainingCategoryRepository;
        this.appointmentMapper = appointmentMapper;
        this.categoryMapper = categoryMapper;
        this.clientServiceRestTemplate = clientServiceRestTemplate;
        this.clientAppointmentMapper = clientAppointmentMapper;
        this.clientAppointmentRepository = clientAppointmentRepository;
        this.messageHelper = messageHelper;
        this.schedulingMessage = schedulingMessage;
    }

    @Override
    public List<AppointmentDto> findAllAppointments(Long clientId) {
        List<ClientAppointment> clientAppointments = clientAppointmentRepository.findByClientID(clientId);

        Set<Long> clientAppointmentIds = clientAppointments.stream()
                .map(clientAppointment -> clientAppointment.getAppointment().getID())
                .collect(Collectors.toSet());

        return appointmentRepository.findAll().stream()
                .filter(appointment -> !clientAppointmentIds.contains(appointment.getID()))
                .map(appointmentMapper::appointmentToAppointmentDto)
                .filter(appointmentDto -> appointmentDto.isAvailability() == true)
                .collect(Collectors.toList());
    }
    @Override
    public int updateTrainingCapacity(ClientAppointmentDto clientAppointmentDto) {
        Appointment appointment = appointmentRepository.findById(clientAppointmentDto.getAppointmentId()).orElse(null);
        int price = appointment.getTrainingCategory().getPrice();
        ResponseEntity<Integer> numberOfTrainings = clientServiceRestTemplate.exchange("/client/numberOfTrainings",
                HttpMethod.PUT, new HttpEntity<>(clientAppointmentDto.getClientId()), Integer.class);
        if (numberOfTrainings.getStatusCode().is2xxSuccessful()){
            System.out.println(numberOfTrainings.getBody() + " body");
            if((numberOfTrainings.getBody() != null && numberOfTrainings.getBody().equals(-1))){
                System.out.printf("Client not found");
                return 0;
            }

            if(numberOfTrainings.getBody() != null && numberOfTrainings.getBody() % 2 == 0) // promeni %2 na %10
                price = 0;
        } else{
            System.out.printf("Los zahtev");
            return 0;
        }

        clientAppointmentRepository.save(clientAppointmentMapper.clientAppointmentDtoToClientAppointment(clientAppointmentDto.getClientId(),appointment));

        appointment.increaseCapacity();
        if(appointment.getCapacity() == 0)
            appointment.setAvailability(false);
        System.out.println(appointment.getCapacity() + " capacity");
        appointmentRepository.save(appointment);

        //String firstName, String lastName, String email, String hallName, String day, String time
        NotificationCreateDto nDto = new NotificationCreateDto(clientAppointmentDto.getFirstName(), clientAppointmentDto.getLastName(),
                clientAppointmentDto.getEmail(), appointment.getHall().getName(), appointment.getDay(), appointment.getStartTime(), clientAppointmentDto.getUsername());
        jmsTemplate.convertAndSend(schedulingMessage, messageHelper.createTextMessage(nDto));
        return price;
    }

    @Override
    public List<AppointmentDto> filterAppointments(FilterDto filterDto) {
        List<AppointmentDto>  filtered = appointmentRepository.findAll().stream().map(appointmentMapper::appointmentToAppointmentDto)
                .filter(appointmentDto -> appointmentDto.isAvailability() == true).collect(Collectors.toList());
        if(!filterDto.getCategory().equals("ignore"))
          filtered = filtered.stream().filter(appointmentDto -> appointmentDto.getTrainingCategory().equals(filterDto.getCategory())).collect(Collectors.toList());
        if(!filterDto.getDay().equals("ignore"))
          filtered = filtered.stream().filter(appointmentDto -> appointmentDto.getDay().equals(filterDto.getDay())).collect(Collectors.toList());
        if(!filterDto.getType().equals("ignore"))
          filtered = filtered.stream().filter(appointmentDto -> appointmentDto.getTrainingType().equals(filterDto.getType())).collect(Collectors.toList());
        return filtered;
    }

    @Override
    public List<CategoryDto> getCategory() {
        return trainingCategoryRepository.findAll().stream().map(categoryMapper::categoryToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public AppointmentDto addAppointment(AppointmentCreateDto appointmentCreateDto) {
        return null;
    }

}