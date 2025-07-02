package com.mayur.HospitalManagementSystem.Repositories;

import com.mayur.HospitalManagementSystem.Entities.Appointment;
import com.mayur.HospitalManagementSystem.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
