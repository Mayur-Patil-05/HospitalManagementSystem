package com.mayur.HospitalManagementSystem.Controllers;

import com.mayur.HospitalManagementSystem.Entities.Doctor;
import com.mayur.HospitalManagementSystem.Entities.Patient;
import com.mayur.HospitalManagementSystem.Exceptions.DoctorNotFoundException;
import com.mayur.HospitalManagementSystem.Exceptions.PatientNotFoundException;
import com.mayur.HospitalManagementSystem.Services.DoctorService;
import com.mayur.HospitalManagementSystem.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.addDoctor(doctor), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        try {
            Doctor existingDoctor = doctorService.getDoctorById(id);

            existingDoctor.setName(doctor.getName());
            existingDoctor.setSpecialization(doctor.getSpecialization());
            existingDoctor.setContact(doctor.getContact());
            Doctor updatedDoctor = doctorService.addDoctor(existingDoctor);
            return new ResponseEntity<>(updatedDoctor, HttpStatus.CREATED);

        } catch (DoctorNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable Long id) {
        try {
            doctorService.deleteDoctorById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
