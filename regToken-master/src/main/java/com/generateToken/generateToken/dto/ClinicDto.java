package com.generateToken.generateToken.dto;

import java.time.LocalTime;

import com.generateToken.generateToken.entities.Doctor;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String incharge;
    private Double fees;
    // @Column(name="pi",length = 6)
    // private Integer pi;
    private LocalTime startTime;
    private LocalTime endTime;
    private Doctor doctor;
    private Integer pincode;

}
