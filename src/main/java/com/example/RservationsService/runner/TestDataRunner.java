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

        TrainingCategory trainingCategory1 = new TrainingCategory("Powerlifting", 3500, "individual");
        TrainingCategory trainingCategory2 = new TrainingCategory("Pilates", 2000, "group");
        TrainingCategory trainingCategory3 = new TrainingCategory("Calistenics", 3000, "individual");
        TrainingCategory trainingCategory4 = new TrainingCategory("Joga", 2500, "group");

        trainingCategoryRepository.saveAll(List.of(trainingCategory1, trainingCategory2, trainingCategory3, trainingCategory4));

        Hall hall1 = new Hall("Hall1", "This hall is placed in Belgrade", 7, trainingCategory1);
        Hall hall2 = new Hall("Hall2", "This hall is placed in Smederevo", 10, trainingCategory2);
        Hall hall3 = new Hall("Hall3", "This hall is placed in Novi Sad", 5, trainingCategory3);
        Hall hall4 = new Hall("Hall4", "This hall is placed in Nis", 12, trainingCategory4);

        hallRepository.saveAll(List.of(hall1, hall2, hall3, hall4));

        // Halla 1
        Appointment appointment1 = new Appointment("12:00", "Monday", true, trainingCategory1, hall1);
        Appointment appointment2 = new Appointment("13:15", "Monday", true, trainingCategory3, hall1);
        Appointment appointment3 = new Appointment("12:00", "Wednesday", true, trainingCategory4, hall1);
        Appointment appointment4 = new Appointment("13:15", "Wednesday", true, trainingCategory3, hall1);
        Appointment appointment5 = new Appointment("12:00", "Friday", true, trainingCategory1, hall1);
        Appointment appointment6 = new Appointment("13:15", "Friday", true, trainingCategory2, hall1);

        // Halla 2
        Appointment appointment7 = new Appointment("12:00", "Monday", true, trainingCategory1, hall2);
        Appointment appointment8 = new Appointment("13:15", "Monday", true, trainingCategory3, hall2);
        Appointment appointment9 = new Appointment("12:00", "Tuesday", true, trainingCategory4, hall2);
        Appointment appointment10 = new Appointment("13:15", "Tuesday", true, trainingCategory4, hall2);
        Appointment appointment11 = new Appointment("12:00", "Thursday", true, trainingCategory1, hall2);
        Appointment appointment12 = new Appointment("13:15", "Thursday", true, trainingCategory2, hall2);

        // Halla 3
        Appointment appointment13 = new Appointment("12:00", "Monday", true, trainingCategory2, hall3);
        Appointment appointment14 = new Appointment("13:15", "Tuesday", true, trainingCategory3, hall3);
        Appointment appointment15 = new Appointment("12:00", "Thursday", true, trainingCategory4, hall3);
        Appointment appointment16 = new Appointment("13:15", "Wednesday", true, trainingCategory3, hall3);
        Appointment appointment17 = new Appointment("12:00", "Friday", true, trainingCategory1, hall3);
        Appointment appointment18 = new Appointment("13:15", "Tuesday", true, trainingCategory2, hall3);

        // Halla 4
        Appointment appointment19 = new Appointment("12:00", "Monday", true, trainingCategory1, hall4);
        Appointment appointment20 = new Appointment("13:15", "Monday", true, trainingCategory3, hall4);
        Appointment appointment21 = new Appointment("12:00", "Tuesday", true, trainingCategory4, hall4);
        Appointment appointment22 = new Appointment("13:15", "Friday", true, trainingCategory4, hall4);
        Appointment appointment23 = new Appointment("12:00", "Thursday", true, trainingCategory1, hall4);
        Appointment appointment24 = new Appointment("13:15", "Friday", true, trainingCategory2, hall4);

     }
}

