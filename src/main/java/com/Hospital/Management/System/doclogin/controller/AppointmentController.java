package com.Hospital.Management.System.doclogin.controller;

import com.Hospital.Management.System.doclogin.entity.Appointment;
import com.Hospital.Management.System.doclogin.repository.AppointmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class AppointmentController {

    private AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping("/insert")
    public Appointment createAppointment(@RequestBody Appointment appointment)
    {
        return appointmentRepository.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointment()
    {
        return appointmentRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteAppointment(@PathVariable long id) throws AttributeNotFoundException
    {
        Appointment appointmentById = appointmentRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("The Appointment id "+id+" Not Found"));
        appointmentRepository.delete(appointmentById);

        Map<String,Boolean> response = new HashMap<String,Boolean>();
        response.put("Deleted",Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
