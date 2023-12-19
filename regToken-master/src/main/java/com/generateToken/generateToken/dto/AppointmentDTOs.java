package com.generateToken.generateToken.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.generateToken.generateToken.Gender.Gender;
import com.generateToken.generateToken.entities.Clinic;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentDTOs {
    private String name;
    private String contact_number;
    private String aadharNumber;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private  String clinicLocation;
    private Clinic clinic;

}
