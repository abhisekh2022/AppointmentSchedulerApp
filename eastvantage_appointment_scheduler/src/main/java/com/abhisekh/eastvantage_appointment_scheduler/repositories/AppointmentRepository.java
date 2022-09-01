package com.abhisekh.eastvantage_appointment_scheduler.repositories;

import com.abhisekh.eastvantage_appointment_scheduler.models.Appointment;
import org.springframework.data.repository.CrudRepository;



public interface AppointmentRepository extends CrudRepository<Appointment, Long>{
    
}
