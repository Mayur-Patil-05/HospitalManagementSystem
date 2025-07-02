package com.mayur.HospitalManagementSystem.Controllers;

import com.mayur.HospitalManagementSystem.Entities.Patient;
import com.mayur.HospitalManagementSystem.Exceptions.PatientNotFoundException;
import com.mayur.HospitalManagementSystem.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientsById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        try {
            Patient patient1 = patientService.getPatientById(id);
            patient1.setName(patient.getName());
            patient1.setEmail(patient.getEmail());
            patient1.setPhone(patient.getPhone());
            patient1.setAddress(patient.getAddress());
            return new ResponseEntity<>(patientService.addPatient(patient1), HttpStatus.CREATED);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatientById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
