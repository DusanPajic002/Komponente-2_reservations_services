package com.example.RservationsService.service.impl;


import com.example.RservationsService.domain.Appointment;
import com.example.RservationsService.domain.ClientAppointment;
import com.example.RservationsService.domain.Hall;
import com.example.RservationsService.domain.TrainingCategory;
import com.example.RservationsService.dto.*;
import com.example.RservationsService.listener.MessageHelper;
import com.example.RservationsService.mapper.AppointmentMapper;
import com.example.RservationsService.mapper.CategoryMapper;
import com.example.RservationsService.mapper.ClientAppointmentMapper;
import com.example.RservationsService.repository.AppointmentRepository;
import com.example.RservationsService.repository.ClientAppointmentRepository;
import com.example.RservationsService.repository.HallRepository;
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
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private MessageHelper messageHelper;
    private AppointmentMapper appointmentMapper;
    private ClientAppointmentMapper clientAppointmentMapper;
    private ClientAppointmentRepository clientAppointmentRepository;
    private TrainingCategoryRepository trainingCategoryRepository;
    private CategoryMapper categoryMapper;
    private RestTemplate clientServiceRestTemplate;
    @Autowired
    private JmsTemplate jmsTemplate;
    private String schedulingMessage;
    private String canceledAppointmentMessage;

    private HallRepository hallRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, TrainingCategoryRepository trainingCategoryRepository, AppointmentMapper appointmentMapper,
                                  CategoryMapper categoryMapper, RestTemplate clientServiceRestTemplate, ClientAppointmentMapper clientAppointmentMapper,
                                  ClientAppointmentRepository clientAppointmentRepository, MessageHelper messageHelper,@Value("${destination.schedulingMessage}") String schedulingMessage,
                                  @Value("${destination.cancelSchedulingMessage}") String canceledAppointmentMessage, HallRepository hallRepository) {
        this.appointmentRepository = appointmentRepository;
        this.trainingCategoryRepository = trainingCategoryRepository;
        this.appointmentMapper = appointmentMapper;
        this.categoryMapper = categoryMapper;
        this.clientServiceRestTemplate = clientServiceRestTemplate;
        this.clientAppointmentMapper = clientAppointmentMapper;
        this.clientAppointmentRepository = clientAppointmentRepository;
        this.messageHelper = messageHelper;
        this.schedulingMessage = schedulingMessage;
        this.canceledAppointmentMessage = canceledAppointmentMessage;
        this.hallRepository = hallRepository;
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
        appointment.increaseCapacity(-1);
        appointmentRepository.save(appointment);

        NotificationCreateDto nDto = new NotificationCreateDto(clientAppointmentDto.getFirstName(), clientAppointmentDto.getLastName(),
                clientAppointmentDto.getEmail(), appointment.getHall().getName(), appointment.getDay(), appointment.getStartTime(), clientAppointmentDto.getUsername());
        jmsTemplate.convertAndSend(schedulingMessage, messageHelper.createTextMessage(nDto));
        return price;
    }

    @Override
    public List<AppointmentDto> filterAppointments(FilterDto filterDto) {

        List<ClientAppointment> clientAppointments = clientAppointmentRepository.findByClientID(filterDto.getClientId());
        Set<Long> clientAppointmentIds = clientAppointments.stream()
                .map(clientAppointment -> clientAppointment.getAppointment().getID())
                .collect(Collectors.toSet());

        List<AppointmentDto>  filtered = appointmentRepository.findAll().stream().map(appointmentMapper::appointmentToAppointmentDto)
                .filter(appointmentDto -> appointmentDto.isAvailability() == true)
                .filter(appointmentDto -> !clientAppointmentIds.contains(appointmentDto.getId()))
                .collect(Collectors.toList());

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

    @Override
    public List<AppointmentDto> getCleintAppointment(String cleintId) {
        return clientAppointmentRepository.findByClientID(Long.parseLong(cleintId))
                .stream().map(clientAppointment -> appointmentMapper.appointmentToAppointmentDto(clientAppointment.getAppointment()))
                .collect(Collectors.toList());
    }
    @Override
    public int cancelAppointment(ClientAppointmentDto clientAppointmentDto) {
       ClientAppointment clientAppointment = clientAppointmentRepository.findByAppointmentIDAndClientID(clientAppointmentDto.getAppointmentId(), clientAppointmentDto.getClientId());
       clientAppointmentRepository.delete(clientAppointment);
       Appointment appointment = appointmentRepository.findById(clientAppointmentDto.getAppointmentId()).orElse(null);
       appointment.increaseCapacity(1);
       appointmentRepository.save(appointment);

       ResponseEntity<Integer> canceledAppointment = clientServiceRestTemplate.exchange("/client/canceledAppointment",
               HttpMethod.PUT, new HttpEntity<>(clientAppointmentDto.getClientId()), Integer.class);

        NotificationCreateDto nDto = new NotificationCreateDto(clientAppointmentDto.getFirstName(), clientAppointmentDto.getLastName(),
                clientAppointmentDto.getEmail(), appointment.getHall().getName(), appointment.getDay(), appointment.getStartTime(), clientAppointmentDto.getUsername());
        jmsTemplate.convertAndSend(canceledAppointmentMessage, messageHelper.createTextMessage(nDto));

        return -appointment.getTrainingCategory().getPrice();
    }

    @Override
    public List<AppointmentDto> listAppointments(String hallName) {
        Hall hall = hallRepository.findByName(hallName);
        List<ClientAppointment> clientAppointments = clientAppointmentRepository.findAll();
        System.out.println(clientAppointments);
        //daj mi sve appointmente iz clientAppointments
        List<Appointment> app =  clientAppointments.stream().map(clientAppointment -> clientAppointment.getAppointment()).collect(Collectors.toList());
        System.out.println(app);
        app = app.stream().filter(appointment -> appointment.getHall().getName().equals(hallName)).collect(Collectors.toList());
        System.out.println(app);
        if (!app.isEmpty()) {
            List<AppointmentDto> ad = app.stream().map(appointmentMapper::appointmentToAppointmentDto).collect(Collectors.toList());
            return ad;
        } else {
            return List.of();
        }
    }


}