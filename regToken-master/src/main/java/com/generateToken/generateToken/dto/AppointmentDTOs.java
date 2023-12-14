package com.generateToken.generateToken.dto;

import com.generateToken.generateToken.Gender.Gender;
import com.generateToken.generateToken.entities.Clinic;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentDTOs {
    private String name;
    private String contactNumber;
    private String aadharNumber;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private  String clinicLocation;
    private Clinic clinic;

}
