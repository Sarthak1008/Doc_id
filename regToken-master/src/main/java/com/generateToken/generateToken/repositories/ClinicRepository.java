package com.generateToken.generateToken.repositories;

import com.generateToken.generateToken.dto.AppointmentDTOs;
import com.generateToken.generateToken.entities.Appointment;
import com.generateToken.generateToken.entities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic,Long> {
   // List<AppointmentDTOs> findByAppointmentDateBetween(Long clinicId, LocalDate startDate, LocalDate endDate);

}
