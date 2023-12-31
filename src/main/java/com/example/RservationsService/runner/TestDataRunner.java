package com.example.RservationsService.runner;

import com.example.RservationsService.domain.Appointment;
import com.example.RservationsService.domain.Hall;
import com.example.RservationsService.domain.TrainingCategory;
import com.example.RservationsService.repository.AppointmentRepository;
import com.example.RservationsService.repository.HallRepository;
import com.example.RservationsService.repository.TrainingCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private AppointmentRepository appointmentRepository;
    private TrainingCategoryRepository trainingCategoryRepository;

    private HallRepository hallRepository;


    public TestDataRunner(AppointmentRepository appointmentRepository, TrainingCategoryRepository trainingCategoryRepository, HallRepository hallRepository) {
        this.appointmentRepository = appointmentRepository;
        this.trainingCategoryRepository = trainingCategoryRepository;
        this.hallRepository = hallRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //String category, int price, String type
        TrainingCategory trainingCategory1 = new TrainingCategory("powerlifting", 1000, "individual");
            TrainingCategory trainingCategory2 = new TrainingCategory("pilates", 2000, "group");
        TrainingCategory trainingCategory3 = new TrainingCategory("calistenics", 3000, "individual");
        TrainingCategory trainingCategory4 = new TrainingCategory("joga", 4000, "group");

        //save all training categories
        trainingCategoryRepository.saveAll(List.of(trainingCategory1, trainingCategory2, trainingCategory3, trainingCategory4));

        //String name, String description, int numberOfTrainers, TrainingCategory trainingCategory
        Hall hall1 = new Hall("hall1", "", 1, trainingCategory1);
        Hall hall2 = new Hall("hall2", "", 2, trainingCategory2);
        Hall hall3 = new Hall("hall3", "", 3, trainingCategory3);
        Hall hall4 = new Hall("hall4", "", 4, trainingCategory4);

        //save all halls
        hallRepository.saveAll(List.of(hall1, hall2, hall3, hall4));

        //String startTime, String day, boolean availability, TrainingCategory trainingCategory, Hall hall
        Appointment appointment1 = new Appointment("12:00", "Monday", true, trainingCategory1, hall1);
        Appointment appointment2 = new Appointment("13:00", "Tuesday", true, trainingCategory2, hall2);
        Appointment appointment3 = new Appointment("14:00", "Wednesday", true, trainingCategory3, hall3);
        Appointment appointment4 = new Appointment("15:00", "Thursday", true, trainingCategory4, hall4);
        //more
        Appointment appointment5 = new Appointment("16:00", "Friday", true, trainingCategory1, hall1);
        Appointment appointment6 = new Appointment("17:00", "Saturday", true, trainingCategory2, hall2);
        Appointment appointment7 = new Appointment("18:00", "Sunday", true, trainingCategory3, hall3);
        Appointment appointment8 = new Appointment("19:00", "Monday", true, trainingCategory4, hall4);
        //more
        Appointment appointment9 = new Appointment("20:00", "Tuesday", false, trainingCategory1, hall1);
        Appointment appointment10 = new Appointment("21:00", "Wednesday", false, trainingCategory2, hall2);
        Appointment appointment11 = new Appointment("22:00", "Thursday", false, trainingCategory3, hall3);
        Appointment appointment12 = new Appointment("23:00", "Friday", false, trainingCategory4, hall4);
        //more, time from 09:00 to 23:00, make me 4 more appointments
        Appointment appointment13 = new Appointment("09:00", "Monday", true, trainingCategory1, hall1);
        Appointment appointment14 = new Appointment("10:00", "Tuesday", true, trainingCategory2, hall2);
        Appointment appointment15 = new Appointment("11:00", "Wednesday", true, trainingCategory3, hall3);
        Appointment appointment16 = new Appointment("12:00", "Thursday", true, trainingCategory4, hall4);
        //more, make me 4 more for monday
        Appointment appointment17 = new Appointment("13:00", "Monday", true, trainingCategory1, hall1);
        Appointment appointment18 = new Appointment("14:00", "Monday", true, trainingCategory2, hall2);
        Appointment appointment19 = new Appointment("15:00", "Monday", true, trainingCategory3, hall3);
        Appointment appointment20 = new Appointment("16:00", "Monday", true, trainingCategory4, hall4);

        //save all appointments
        appointmentRepository.saveAll(List.of(appointment1, appointment2, appointment3, appointment4, appointment5, appointment6, appointment7, appointment8, appointment9, appointment10, appointment11, appointment12, appointment13, appointment14, appointment15, appointment16, appointment17, appointment18, appointment19, appointment20));
    }
}

