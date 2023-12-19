package com.generateToken.generateToken.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.generateToken.generateToken.Gender.Gender;
import com.generateToken.generateToken.dto.AppointmentDTOs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @Column(name = "contact_number", length = 255)
    private String contact_number; // Updated to use contactNumber as the primary key

    private String name;
    private String aadharNumber;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private  String clinicLocation;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "clinicId")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    public Appointment() {
    }

    public AppointmentDTOs getAppointmentDto(){
        AppointmentDTOs appointmentDTOs = new AppointmentDTOs();
        appointmentDTOs.setName(this.getName());
        appointmentDTOs.setContact_number(this.getContact_number());
        appointmentDTOs.setAadharNumber(this.aadharNumber);
        appointmentDTOs.setAge(this.age);
        appointmentDTOs.setGender(this.gender);
        appointmentDTOs.setAppointmentDate(this.appointmentDate);
        appointmentDTOs.setAppointmentTime(this.appointmentTime);
        appointmentDTOs.setClinicLocation(this.clinicLocation);
        return appointmentDTOs;
    }

}
