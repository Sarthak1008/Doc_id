package com.generateToken.generateToken.services;

import com.generateToken.generateToken.dto.AppointmentDTOs;
import com.generateToken.generateToken.entities.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    //String bookAppointment(AppointmentDTOs appointmentDto);
    Appointment getByAadhar(String aadharCard);

    AppointmentDTOs bookAppointment(Long doctorId,Long clinicId,AppointmentDTOs appointmentPatient );

 //   List<AppointmentDTOs> getAppointmentBetweenDate(Long clinicId, LocalDate startDate, LocalDate endDate);
}
