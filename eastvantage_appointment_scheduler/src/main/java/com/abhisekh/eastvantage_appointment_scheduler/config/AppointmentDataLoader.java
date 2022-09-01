package com.abhisekh.eastvantage_appointment_scheduler.config;

import com.abhisekh.eastvantage_appointment_scheduler.models.Appointment;
import com.abhisekh.eastvantage_appointment_scheduler.repositories.AppointmentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDataLoader implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(AppointmentDataLoader.class);

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if (appointmentRepository.count() == 0) {
            Appointment appointment1 = new Appointment("Interview with Eastvantage");
            Appointment appointment2 = new Appointment("Onboarding with Eastvantage");

            appointmentRepository.save(appointment1);
            appointmentRepository.save(appointment2); 
        }

        logger.info("Number of Appointments: {}", appointmentRepository.count());
    }
}
