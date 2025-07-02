package com.mayur.HospitalManagementSystem.Services;

import com.mayur.HospitalManagementSystem.Entities.Doctor;
import com.mayur.HospitalManagementSystem.Entities.Patient;
import com.mayur.HospitalManagementSystem.Exceptions.DoctorNotFoundException;
import com.mayur.HospitalManagementSystem.Exceptions.PatientNotFoundException;
import com.mayur.HospitalManagementSystem.Repositories.DoctorRepository;
import com.mayur.HospitalManagementSystem.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) throws DoctorNotFoundException {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with Id: " + id));
    }

    public void deleteDoctorById(Long id) throws DoctorNotFoundException {
        if (!doctorRepository.existsById(id)) {
            throw new DoctorNotFoundException("Doctor not found with Id: " + id);
        }
        doctorRepository.deleteById(id);
    }
}
