package com.generateToken.generateToken.dto;

import java.time.LocalDate;
import java.util.List;

import com.generateToken.generateToken.Gender.Gender;
import com.generateToken.generateToken.entities.MedicinePrescription;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;
    private Long clinicId;
    private LocalDate currDate;
    private String doctorName;
    private Long patientId;
    private int patientAge;
    private Gender gender;
    private List<MedicinePrescription> medicinesPrescribed;
    private LocalDate startDateOfMedication;
    private LocalDate endDateOfMedication;
    private String contactOfClinic;
    private String speciality;
    private String location;

    // Constructors, getters, and setters...

}
