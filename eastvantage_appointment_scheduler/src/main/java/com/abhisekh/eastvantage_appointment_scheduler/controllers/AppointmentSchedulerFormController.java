package com.abhisekh.eastvantage_appointment_scheduler.controllers;

import com.abhisekh.eastvantage_appointment_scheduler.models.Appointment;
import com.abhisekh.eastvantage_appointment_scheduler.repositories.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppointmentSchedulerFormController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/create-schedule")
    public String showCreateForm(Appointment appointment){
        return "add-appointment";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Appointment appointment = appointmentRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Appointment id: " + id + " not found"));
    
        model.addAttribute("schedule", appointment);
        return "update-appointment";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") long id, Model model) {
        Appointment appointment = appointmentRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Appointment id: " + id + " not found"));
    
        appointmentRepository.delete(appointment);
        return "redirect:/";
    }
}
