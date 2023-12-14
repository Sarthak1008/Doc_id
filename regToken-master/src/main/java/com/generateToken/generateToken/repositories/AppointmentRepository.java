package com.generateToken.generateToken.repositories;

import com.generateToken.generateToken.dto.AppointmentDTOs;
import com.generateToken.generateToken.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    @Query(value = "select count(*) from appointment where clinic_id = :clinicId  and  appointment_date <= :endDate and  appointment_date >= :startDate ",nativeQuery = true)
    public int findByAppointmentDateBetween(Long clinicId, Date startDate, Date endDate);

}
