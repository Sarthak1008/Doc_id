package com.generateToken.generateToken.repositories;

import com.generateToken.generateToken.dto.AppointmentDTOs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generateToken.generateToken.entities.Clinic;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic,Long> {

//    void delete(Clinic byId);
//    List<AppointmentDTOs> findByAppointmentDateBetween(Long clinicId, LocalDate startDate, LocalDate endDate);

}
