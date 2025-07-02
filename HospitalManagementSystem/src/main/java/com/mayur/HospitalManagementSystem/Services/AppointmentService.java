package com.mayur.HospitalManagementSystem.Services;

import com.mayur.HospitalManagementSystem.Entities.Appointment;
import com.mayur.HospitalManagementSystem.Entities.Doctor;
import com.mayur.HospitalManagementSystem.Entities.Patient;
import com.mayur.HospitalManagementSystem.Exceptions.AppointmentNotFoundException;
import com.mayur.HospitalManagementSystem.Exceptions.DoctorNotFoundException;
import com.mayur.HospitalManagementSystem.Exceptions.PatientNotFoundException;
import com.mayur.HospitalManagementSystem.Repositories.AppointmentRepository;
import com.mayur.HospitalManagementSystem.Repositories.DoctorRepository;
import com.mayur.HospitalManagementSystem.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public Appointment bookAppointment(Long patientId, Long doctorId, Appointment appointment)
            throws PatientNotFoundException, DoctorNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with Id: " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new PatientNotFoundException("Doctor not found with Id: " + doctorId));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStatus("SCHEDULED");
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointById(Long id) throws AppointmentNotFoundException {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with Id: " + id));
    }

    public void deleteAppointById(Long id) throws AppointmentNotFoundException {
        if (!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException("Appointment not found with Id: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}
