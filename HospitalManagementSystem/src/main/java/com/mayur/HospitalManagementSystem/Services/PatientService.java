package com.mayur.HospitalManagementSystem.Services;

import com.mayur.HospitalManagementSystem.Entities.Patient;
import com.mayur.HospitalManagementSystem.Exceptions.PatientNotFoundException;
import com.mayur.HospitalManagementSystem.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) throws PatientNotFoundException {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with Id: " + id));
    }

    public void deletePatientById(Long id) throws PatientNotFoundException {
        Optional<Patient> findPatient = patientRepository.findById(id);
        if (findPatient.isEmpty()) {
            throw new PatientNotFoundException("Patient not found with Id: " + id);
        }
        patientRepository.deleteById(id);
    }
}
