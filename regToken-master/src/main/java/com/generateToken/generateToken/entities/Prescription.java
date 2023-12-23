package com.generateToken.generateToken.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.generateToken.generateToken.Gender.Gender;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prescription")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @Column(name = "currDate")
    private LocalDate currDate ;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Transient
    private String docName;

    @Transient
    private String degree;

    @Transient
    private String speciality;


  @Transient
  private LocalTime startTime;

  @Transient
  private LocalTime endTime;

  @Transient
  private String contact;

  @Transient
  private String patient_Name;

  @Transient
  private int age;

  @Transient
  private String location;

  @ManyToOne()
  @JoinColumn(name = "patient_contact_number")
  private Appointment appointment;
  //cascade = CascadeType.ALL

  @JsonIgnore
  @ManyToOne()
  @JoinColumn(name = "clinicId")
  private Clinic clinic;

  @ManyToOne
  @JoinColumn(name = "doctorId")
  private Doctor doctor;

}
