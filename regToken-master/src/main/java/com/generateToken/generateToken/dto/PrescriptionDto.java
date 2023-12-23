package com.generateToken.generateToken.dto;

import java.time.LocalDate;
import java.util.List;

import com.generateToken.generateToken.Gender.Gender;
import com.generateToken.generateToken.entities.MedicinePrescription;

import jakarta.persistence.*;
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
    private LocalDate currDate ;

    private Gender gender;



}

