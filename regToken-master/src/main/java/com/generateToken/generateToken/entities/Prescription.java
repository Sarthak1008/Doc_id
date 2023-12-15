package com.generateToken.generateToken.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.generateToken.generateToken.Gender.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "prescription")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinicId", referencedColumnName = "id")
    private Clinic clinic;

    @Column(name ="currDate")
    private LocalDate currDate = LocalDate.now();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctorName", referencedColumnName = "name")
    private Doctor doctor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId", referencedColumnName = "id")
    private Appointment appointmentId;

    private int patientAge = (appointmentId != null) ? appointmentId.getAge() : 0;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    List<HashMap<String, Integer>> medicinesPrescribed = new ArrayList<>();

    @Column(name = "start_date_of_medication")
    private LocalDate startDateOfMedication;

    @Column(name = "end_date_of_medication")
    private LocalDate endDateOfMedication;

    @Column(name = "contact_of_clinic")
    private String contactOfClinic = (doctor != null) ? doctor.getContact() : null;

    @Column(name = "speciality")
    private String speciality = (doctor != null) ? doctor.getSpecialization() : null;

    @Column(name = "location")
    private String location = (clinic != null) ? clinic.getLocation() : null;
}
