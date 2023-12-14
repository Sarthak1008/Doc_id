package com.generateToken.generateToken.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.generateToken.generateToken.Gender.Gender;
import com.generateToken.generateToken.dto.AppointmentDTOs;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String contactNumber;
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

    public AppointmentDTOs getAppointmentDto(){
        AppointmentDTOs appointmentDTOs = new AppointmentDTOs();
        appointmentDTOs.setName(this.getName());
        appointmentDTOs.setContactNumber(this.contactNumber);
        appointmentDTOs.setAadharNumber(this.aadharNumber);
        appointmentDTOs.setAge(this.age);
        appointmentDTOs.setGender(this.gender);
        appointmentDTOs.setAppointmentDate(this.appointmentDate);
        appointmentDTOs.setAppointmentTime(this.appointmentTime);
        appointmentDTOs.setClinicLocation(this.clinicLocation);
        return appointmentDTOs;
    }

}
