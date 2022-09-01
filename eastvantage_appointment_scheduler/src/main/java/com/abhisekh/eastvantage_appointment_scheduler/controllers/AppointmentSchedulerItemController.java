package com.abhisekh.eastvantage_appointment_scheduler.controllers;

import java.time.Instant;
import java.time.ZoneId;
import javax.validation.Valid;

import com.abhisekh.eastvantage_appointment_scheduler.models.Appointment;
import com.abhisekh.eastvantage_appointment_scheduler.repositories.AppointmentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class AppointmentSchedulerItemController {
    private final Logger logger = LoggerFactory.getLogger(AppointmentSchedulerItemController.class);
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/")
    public ModelAndView index() {
        logger.info("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("appointments", appointmentRepository.findAll());
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }

    @PostMapping("/schedule")
    public String createAppointment(@Valid Appointment appointment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-appointment";
        }

        appointment.setCreatedDate(Instant.now());
        appointment.setModifiedDate(Instant.now());
        appointmentRepository.save(appointment);
        return "redirect:/";
    }

    @PostMapping("/schedule/{id}")
    public String updateAppointment(@PathVariable("id") long id, @Valid Appointment appointment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            appointment.setId(id);
            return "update-appointment";
        }

        appointment.setModifiedDate(Instant.now());
        appointmentRepository.save(appointment);
        return "redirect:/";
    }

}
