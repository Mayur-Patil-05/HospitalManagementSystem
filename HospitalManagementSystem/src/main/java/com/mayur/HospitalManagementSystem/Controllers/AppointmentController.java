package com.mayur.HospitalManagementSystem.Controllers;

import com.mayur.HospitalManagementSystem.Entities.Appointment;
import com.mayur.HospitalManagementSystem.Exceptions.AppointmentNotFoundException;
import com.mayur.HospitalManagementSystem.Exceptions.DoctorNotFoundException;
import com.mayur.HospitalManagementSystem.Exceptions.PatientNotFoundException;
import com.mayur.HospitalManagementSystem.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book/{patientId}/{doctorId}")
    public ResponseEntity<Appointment> bookAppointment(
            @PathVariable Long patientId,
            @PathVariable Long doctorId,
            @RequestBody Appointment appointment) {
        try {
            return new ResponseEntity<>(appointmentService
                    .bookAppointment(patientId, doctorId, appointment),
                    HttpStatus.CREATED);
        } catch (PatientNotFoundException | DoctorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return new ResponseEntity<>(appointmentService.getAllAppointments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(appointmentService.getAppointById(id), HttpStatus.OK);
        } catch (AppointmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (AppointmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
